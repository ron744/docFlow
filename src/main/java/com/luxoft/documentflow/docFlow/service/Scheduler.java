package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.DocumentStack;
import com.luxoft.documentflow.docFlow.model.Workflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class Scheduler {

    private final DocumentService documentService;
    private final DocumentServiceCrud documentServiceCrud;
    private final WorkflowServiceCrud workflowServiceCrud;
    private final StateService stateService;

    private final ArrayBlockingQueue<Document> documents = new ArrayBlockingQueue<>(20000);
    private final ArrayBlockingQueue<DocumentStack> documentStackForWork = new ArrayBlockingQueue<>(20000);
    private DocumentStack documentStack = null;

    private final Object monitor1 = new Object();

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
                        List<Document> documentList = documentStackForWork.take().getDocuments();

                        List<Workflow> workflowList = stateService.processing(documentList);

                        if (workflowList.size() > 0) {
                            workflowServiceCrud.add(workflowList.get(0));
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
        Long workflowId = doc.getWorkflowId();

        synchronized (monitor1) {

            if (documentStack == null || !documentStack.getWorkflowId().equals(workflowId)) {

                System.out.println("documentStack 1: " + documentStack);
                if (documentStack != null) {
                    documentStackForWork.add(documentStack);
                }

                documentStack = new DocumentStack(workflowId, new ArrayList<>());
            }

            documentStack.getDocuments().add(doc);
            System.out.println("documentStack 2: " + documentStack);
        }

    }


    @Scheduled(fixedDelay = 7000)
    public void generateRandomDocument() {
        int countId = 1;
        List<Document> documentList = documentService.addRandomDocument(countId);
        documents.addAll(documentList);
    }
}
