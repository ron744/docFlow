package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.DocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DocumentGenerator {
    @Autowired
    AutowireCapableBeanFactory beanFactory;

    public List<Document> generate(int workflowId) {
        List<Document> documents = new ArrayList<>();

        for (int i = 1; i <= workflowId; i++) {
            Document primaryDocument = generatePrimaryDocument((long) i);
            int documentCount = ThreadLocalRandom.current().nextInt(1, 7);

            for (int j = 1; j <= documentCount; j++) {
                documents.add(generateDocument((long) i));
            }

            documents.add(primaryDocument);
        }

        for (Document document : documents) {
            System.out.println(document.toString());
        }

        System.out.println("----------document generation is finished----------");

        return documents;
    }

    public Document generatePrimaryDocument(Long workflowId) {
        String name = new Random().toString();

        return new Document(workflowId, name, workflowId, null, true);
    }

    public Document generateDocument(Long workflowId) {
        String name = new Random().toString();
        Long randomId = ThreadLocalRandom.current().nextLong(300, 200000);
        int docTypeNumber = ThreadLocalRandom.current().nextInt(0, DocType.values().length);
        DocType docType = DocType.values()[docTypeNumber];

        Document document = null;
        try {
            document = new DocumentFactory(randomId, name, workflowId, docType, false).getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return document;
    }
}
