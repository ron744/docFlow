package com.luxoft.documentflow.docFlow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    private final WorkflowServiceCrud workflowServiceCrud;

    @Autowired
    public WorkflowService(WorkflowServiceCrud workflowServiceCrud) {
        this.workflowServiceCrud = workflowServiceCrud;
    }
}
