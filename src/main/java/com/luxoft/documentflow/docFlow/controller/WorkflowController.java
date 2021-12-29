package com.luxoft.documentflow.docFlow.controller;

import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.service.Scheduler;
import com.luxoft.documentflow.docFlow.service.StateService;
import com.luxoft.documentflow.docFlow.service.WorkflowServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "api/v1/workflow")
public class WorkflowController {

    private final WorkflowServiceCrud workflowServiceCrud;
    private final StateService stateService;

    @Autowired
    public WorkflowController(WorkflowServiceCrud workflowServiceCrud, StateService stateService) {
        this.workflowServiceCrud = workflowServiceCrud;
        this.stateService = stateService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Success";
    }

    @PostMapping(value = "/getAllByIds")
    public ResponseEntity<Collection<Workflow>> getAllByIds(@RequestBody List<Long> ids) {

        Collection<Workflow> workflows = null;

        try {
            workflows = (Collection<Workflow>) workflowServiceCrud.findAllById(ids);
            return ResponseEntity.ok(workflows);

        } catch (NoSuchElementException e) {

            return ResponseEntity.status(500).body(workflows);
        }
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<Workflow> getById(@RequestParam Long id) {
        Workflow workflow = workflowServiceCrud.findWorkflowById(id);
        if (workflow != null) {
            return ResponseEntity.ok(workflowServiceCrud.findWorkflowById(id));
        }

        return ResponseEntity.status(500).body(new Workflow());
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Workflow> save(@RequestBody Workflow workflow) {
        return ResponseEntity.ok(workflowServiceCrud.add(workflow));
    }

    @GetMapping(value = "/addRandomDoc")
    public ResponseEntity<List<Workflow>> addWorkflowWithStateFromDocuments(@RequestParam int countId) {
        List<Workflow> workflowList = stateService.processingWithRandomDoc(countId);

        List<Workflow> workflowsWithState = (List<Workflow>) workflowServiceCrud.addAll(workflowList);

        return ResponseEntity.ok(workflowsWithState);
    }
}
