package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class DocumentGeneratorTest {

    @Test
    public void generateTest() {
        Collection<Document> documentList = new DocumentGenerator().generate();
        for (Document doc : documentList) {
            System.out.println(doc.toString());
        }
    }
}
