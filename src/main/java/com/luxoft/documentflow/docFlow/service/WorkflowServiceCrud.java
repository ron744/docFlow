package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceCrud {

    private final WorkflowRepository repository;

    @Autowired
    public WorkflowServiceCrud(WorkflowRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Workflow> add(Workflow workflow) {
        return ResponseEntity.ok(repository.save(workflow));
    }

    public ResponseEntity<Iterable<Workflow>> findAllById(Iterable<Long> ids) {
        return ResponseEntity.ok(repository.findAllById(ids));
    }
}
