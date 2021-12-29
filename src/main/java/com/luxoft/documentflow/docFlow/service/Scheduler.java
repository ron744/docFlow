package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.DocumentStack;
import com.luxoft.documentflow.docFlow.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class Scheduler {

    private final DocumentService documentService;

    private final ArrayBlockingQueue<Document> documents = new ArrayBlockingQueue<>(20000);
    private final ArrayBlockingQueue<DocumentStack> documentStackForWork = new ArrayBlockingQueue<>(20000);
    private DocumentStack documentStack = null;

    private final Object monitor1 = new Object();

    @Autowired
    public Scheduler(DocumentService documentService, DocumentServiceCrud documentServiceCrud, WorkflowServiceCrud workflowServiceCrud, StateService stateService) {
        this.documentService = documentService;

        for (int i = 0; i < 3; i++) {
            Thread th = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {

                        List<Document> documentList = documentStackForWork.take().getDocuments();

                        if (documentList.size() > 0) {
                            List<Workflow> workflowList = stateService.processing(documentList);

                            if (workflowList.size() > 0) {
                                workflowServiceCrud.add(workflowList.get(0));
                            }
                        }

                    } catch (InterruptedException e) {
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

        synchronized (monitor1) {
            Long workflowId = doc.getWorkflowId();

            if (documentStack == null || !documentStack.getWorkflowId().equals(workflowId)) {

                if (documentStack != null) {
                    documentStackForWork.add(documentStack);
                }

                documentStack = new DocumentStack(workflowId, new ArrayList<>());
            }

            documentStack.getDocuments().add(doc);
        }

    }


    @Scheduled(fixedDelay = 300000)
    public void generateRandomDocument() {
        int countId = 10;
        List<Document> documentList = documentService.addRandomDocument(countId);
        documents.addAll(documentList);
    }
}
