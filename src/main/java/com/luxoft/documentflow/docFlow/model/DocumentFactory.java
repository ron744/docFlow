package com.luxoft.documentflow.docFlow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

@Data
@AllArgsConstructor
public class DocumentFactory implements FactoryBean<Document> {
    private Long id;
    private String name;
    private Long workflowId;
    private DocType docType;
    private boolean isPrimary;

    @Override
    public Document getObject() throws Exception {
        return new Document(id, name, workflowId, docType, isPrimary);
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
