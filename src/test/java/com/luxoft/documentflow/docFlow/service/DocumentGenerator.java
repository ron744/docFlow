package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DocumentGenerator {

    public Map<Document, Collection<Document>> generate() {
        int workflowId = 10;
        Map<Document, Collection<Document>> documentMap = new HashMap<>();

        for (int i = 0; i < workflowId; i++) {
            Document primaryDocument = generatePrimaryDocument((long) i);
            int documentCount = ThreadLocalRandom.current().nextInt(1, 10);

            List<Document> documentList = new ArrayList<>();
            for (int j = 1; j <= documentCount; j++) {
                documentList.add(generateDocument((long) i));
            }

            documentMap.put(primaryDocument, documentList);
        }

        return documentMap;
    }

    public Document generatePrimaryDocument(Long workflowId) {
        String name = new Random().toString();

        return new Document(name, workflowId, null, true);
    }

    public Document generateDocument(Long workflowId) {
        String name = new Random().toString();
        int docTypeNumber = ThreadLocalRandom.current().nextInt(0, DocType.values().length);
        DocType docType = DocType.values()[docTypeNumber];

        return new Document(name, workflowId, docType, false);
    }

    @Test
    public void generateTest() {
        Map<Document, Collection<Document>> documentMap = generate();
        for (Map.Entry<Document, Collection<Document>> entry : documentMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
    }
}
