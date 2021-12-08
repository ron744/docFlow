package com.luxoft.documentflow.docFlow.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Workflow {
    @Id
    private Long id;
    private String name;
    private WorkflowStatus workflowStatus;
}
