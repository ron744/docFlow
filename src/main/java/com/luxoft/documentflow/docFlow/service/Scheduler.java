package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.DocumentStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Scheduler {

    private final DocumentService documentService;
    private final DocumentServiceCrud documentServiceCrud;
    private final WorkflowServiceCrud workflowServiceCrud;
    private final StateService stateService;

    private final ArrayBlockingQueue<Document> documents = new ArrayBlockingQueue<>(200);
//    private Map<Long, List<Document>> documentByWorkflowId = new ConcurrentHashMap<>();
    private List<DocumentStack> documentByWorkflowId = new LinkedList<>();

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    @Autowired
    public Scheduler(DocumentService documentService, DocumentServiceCrud documentServiceCrud, WorkflowServiceCrud workflowServiceCrud, StateService stateService) {
        this.documentService = documentService;
        this.documentServiceCrud = documentServiceCrud;
        this.workflowServiceCrud = workflowServiceCrud;
        this.stateService = stateService;

        for (int i = 0; i < 3; i++) {
            Thread th = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        if (documentByWorkflowId.size() > 1) {

                            synchronized (monitor2) {
                                stateService.processing(documentByWorkflowId.get(0).getDocuments());
                                documentByWorkflowId.remove(0);
                            }

                        }
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            th.setDaemon(true);
            th.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread th = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Document doc = documents.take();

                        addDocToDocumentByWorkflowId(doc);

                        documentServiceCrud.add(doc);

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            th.setDaemon(true);
            th.start();
        }
    }

    private void addDocToDocumentByWorkflowId(Document doc) {
        List<Document> documentList = new ArrayList<>();
        Long workflowId = doc.getWorkflowId();

        boolean documentStackChanged = false;

        synchronized (monitor1) {

            for (int i = 0; i < documentByWorkflowId.size(); i++) {
                DocumentStack documentStack = documentByWorkflowId.get(i);

                if (documentStack.getWorkflowId().equals(workflowId)) {
                    documentList = documentStack.getDocuments();
                    documentStack.setDocuments(documentList);
                    documentByWorkflowId.set(i, documentStack);
                    documentStackChanged = true;
                    break;
                }
            }

            if (!documentStackChanged) {
                documentList.add(doc);
                documentByWorkflowId.add(new DocumentStack(workflowId, documentList));
            }

//            if (documentByWorkflowId.containsKey(workflowId)) {
//                documentList = documentByWorkflowId.get(workflowId);
//            }
//            documentList.add(doc);
//            documentByWorkflowId.put(workflowId, documentList);

        }
    }



    @Scheduled
    public void generateRandomDocument() {
        int countId = 100;
        List<Document> documentList = documentService.addRandomDocument(countId);
        documents.addAll(documentList);
    }
}
