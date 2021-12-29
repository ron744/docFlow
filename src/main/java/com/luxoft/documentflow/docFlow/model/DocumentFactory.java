package com.luxoft.documentflow.docFlow.model;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

@Data
public class DocumentFactory implements FactoryBean<Document> {
    private Document document;

    public DocumentFactory(Long randomId, String name, Long workflowId, DocType docType, boolean isPrimary) {
        this.document = new Document(randomId, name, workflowId, docType, isPrimary);
    }

    @Override
    public Document getObject() throws Exception {
        return document;
    }

    @Override
    public Class<?> getObjectType() {
        return Document.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
