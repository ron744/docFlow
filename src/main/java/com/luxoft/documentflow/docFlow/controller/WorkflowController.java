package com.luxoft.documentflow.docFlow.controller;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.service.DocumentService;
import com.luxoft.documentflow.docFlow.service.StateService;
import com.luxoft.documentflow.docFlow.service.WorkflowServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "v1/api/workflow")
public class WorkflowController {

    private final WorkflowServiceCrud workflowServiceCrud;
    private final DocumentService documentService;
    private final StateService stateService;

    @Autowired
    public WorkflowController(WorkflowServiceCrud workflowServiceCrud, DocumentService documentService, StateService stateService) {
        this.workflowServiceCrud = workflowServiceCrud;
        this.documentService = documentService;
        this.stateService = stateService;
    }

    @GetMapping(value = "/getAllByIds")
    public ResponseEntity<Collection<Workflow>> getAllByIds(@RequestBody List<Long> ids) {

        Collection<Workflow> workflows = null;

        try {
            workflows = (Collection<Workflow>) workflowServiceCrud.findAllById(ids);
            return ResponseEntity.ok(workflows);

        } catch (NoSuchElementException e) {

            return ResponseEntity.status(500).body(workflows);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Workflow> save(@RequestBody Workflow workflow) {
        return ResponseEntity.ok(workflowServiceCrud.add(workflow));
    }

    @GetMapping(value = "/addRandomDoc")
    public ResponseEntity<List<Workflow>> addWorkflowWithStateFromDocuments() {
        List<Document> documentList = documentService.addRandomDocument();
        List<Workflow> workflowList = stateService.processing(documentList);

        List<Workflow> workflowsWithState = (List<Workflow>) workflowServiceCrud.addAll(workflowList);

        return ResponseEntity.ok(workflowsWithState);
    }
}
