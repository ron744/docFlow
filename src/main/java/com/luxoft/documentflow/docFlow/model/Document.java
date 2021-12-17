package com.luxoft.documentflow.docFlow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
