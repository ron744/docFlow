package com.luxoft.documentflow.docFlow.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long workflowId;
    private DocType docType;
    private boolean isPrimary;

    public Document(String name, Long workflowId, DocType docType, boolean isPrimary) {
        this.name = name;
        this.workflowId = workflowId;
        this.docType = docType;
        this.isPrimary = isPrimary;
    }
}
