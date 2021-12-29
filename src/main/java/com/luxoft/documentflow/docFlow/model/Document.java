package com.luxoft.documentflow.docFlow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
public class Document {
    @Id
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
