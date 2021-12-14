package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.model.WorkflowStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
@NoArgsConstructor
public class StatesMachine {

    private WorkflowServiceCrud serviceCrud;

    @Autowired
    public StatesMachine(WorkflowServiceCrud serviceCrud) {
        this.serviceCrud = serviceCrud;
    }

    // посмотреть, что такое конечный автомат
    public List<Workflow> processing1(List<Document> documentList) {
        Map<Long, Collection<DocType>> workflowDocumentTypesMap = new HashMap<>();
        Map<Long, Workflow> workflowMap = new HashMap<>();

        for (Document doc : documentList) {
            if (doc.isPrimary()) {
                workflowMap.put(doc.getWorkflowId(), new Workflow(doc.getWorkflowId(), doc.getName(), null));
            }
            workflowDocumentTypesMap.putIfAbsent(doc.getWorkflowId(), new HashSet<>());
            Collection<DocType> docTypes = workflowDocumentTypesMap.get(doc.getWorkflowId());
            if (doc.getDocType() != null) {
                docTypes.add(doc.getDocType());
            }
        }

        List<Workflow> result = new ArrayList<>(workflowMap.values().size());
        for (Workflow workflow : workflowMap.values()) {
            Collection<DocType> documentTypes = workflowDocumentTypesMap.get(workflow.getId());
            WorkflowStatus workflowStatus = calcStatus(documentTypes);
            workflow.setWorkflowStatus(workflowStatus);
            result.add(workflow);
        }

        return result;
    }

    public List<Workflow> processing(List<Document> documentList) {
        Collection<Workflow> workflowsFromBd = getWorkflowsFromDbById(getUniqueWorkflowIds(documentList));
        return new ArrayList<>();
    }

    public Set<Long> getUniqueWorkflowIds(List<Document> documentList) {
        return documentList.stream()
                .map(Document::getWorkflowId)
                .collect(Collectors.toSet());
    }

    public Collection<Workflow> getWorkflowsFromDbById(Collection<Long> ids) {
        return (Collection<Workflow>) serviceCrud.findAllById(ids).getBody();
    }

    public WorkflowStatus calcStatus(Collection<DocType> docTypeList) {
        WorkflowStatus workflowStatus = null;

        if (docTypeList.contains(DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender)) {
            workflowStatus = WorkflowStatus.FINISHED;
        } else if (docTypeList.contains(DocType.notification_of_receipt_of_the_signature_from_the_sender)) {
            workflowStatus = WorkflowStatus.SIGNED;
        } else if (docTypeList.contains(DocType.signing_of_the_primary_document_from_the_recipient)) {
            workflowStatus = WorkflowStatus.WAITING_FOR_SIGN;
        } else if (docTypeList.contains(DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document)) {
            workflowStatus = WorkflowStatus.WAITING_FOR_RECEIPT_CONFIRMATION;
        } else if (docTypeList.contains(DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient)) {
            workflowStatus = WorkflowStatus.NEW;
        }

        return workflowStatus;
    }
}
