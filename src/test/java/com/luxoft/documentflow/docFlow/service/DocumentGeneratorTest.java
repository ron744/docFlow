package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class DocumentGeneratorTest {

    @Test
    public void generateTest() {
        int countId = 2;
        Collection<Document> documentList = new DocumentGenerator().generate(countId);
        for (Document doc : documentList) {
            System.out.println(doc.toString());
        }
    }
}
