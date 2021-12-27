package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.model.WorkflowState;
import com.luxoft.documentflow.docFlow.model.state.StateMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class StateService {

    private final WorkflowServiceCrud serviceCrud;
    private final DocumentService documentService;

    @Autowired
    public StateService(WorkflowServiceCrud serviceCrud, DocumentService documentService) {
        this.serviceCrud = serviceCrud;
        this.documentService = documentService;
    }

    public List<Workflow> processing(List<Document> documentList) {
        Map<Long, Collection<DocType>> workflowDocumentTypesMap = new HashMap<>();
        Map<Long, Workflow> workflowMap = new HashMap<>();

        for (Document document : documentList) {
            System.out.println(document.toString());
        }
        System.out.println("-------------------------------");

        for (Document doc : documentList) {
            Long workflowId = doc.getWorkflowId();

            if (doc.isPrimary()) {

                Workflow workflow = findWorkflowById(workflowId);
                if (workflow != null) {
                    workflowMap.put(workflowId, workflow);
                } else {
                    workflowMap.put(workflowId, new Workflow(workflowId, doc.getName(), null));
                }

            }
            workflowDocumentTypesMap.putIfAbsent(workflowId, new HashSet<>());
            Collection<DocType> docTypes = workflowDocumentTypesMap.get(workflowId);
            if (doc.getDocType() != null) {
                docTypes.add(doc.getDocType());
            }
        }

        List<Workflow> result = new ArrayList<>(workflowMap.values().size());
        for (Workflow workflow : workflowMap.values()) {
            Collection<DocType> documentTypes = workflowDocumentTypesMap.get(workflow.getId());
            WorkflowState workflowState = new StateMachine().changeState(documentTypes, workflow.getWorkflowState());
            workflow.setWorkflowState(workflowState);
            result.add(workflow);
        }

        return result;
    }

    public List<Workflow> processingWithRandomDoc(int countId) {
        List<Document> documentList = documentService.addRandomDocument(countId);
        return processing(documentList);
    }

    public Workflow findWorkflowById(Long id) {
        return serviceCrud.findWorkflowById(id);
    }

    public Collection<Workflow> getWorkflowFromBdById(List<Long> ids) {
        Iterable<Workflow> response = serviceCrud.findAllById(ids);
        return (Collection<Workflow>) response;
    }
}
