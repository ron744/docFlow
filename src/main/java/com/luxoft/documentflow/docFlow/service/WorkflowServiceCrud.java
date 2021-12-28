package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.repository.WorkflowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WorkflowServiceCrud {

    private final WorkflowRepository repository;

    @Autowired
    public WorkflowServiceCrud(WorkflowRepository repository) {
        this.repository = repository;
    }

    public Workflow add(Workflow workflow) {
//        System.out.println("add workflow: " + workflow.toString());
        return repository.save(workflow);
    }

    public Iterable<Workflow> addAll(Iterable<Workflow> workflows) {
        return repository.saveAll(workflows);
    }

    public Iterable<Workflow> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    public Workflow findWorkflowById(Long id) {
        Optional<Workflow> workflowOptional = repository.findById(id);
        if (workflowOptional.isPresent()) {
            return workflowOptional.get();
        }

        throw new NoSuchElementException();
    }
}
