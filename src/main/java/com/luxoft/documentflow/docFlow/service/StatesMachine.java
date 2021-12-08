package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.model.WorkflowStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StatesMachine {

    public List<Workflow> processing(List<Document> documentList) {
        List<Workflow> workflowList = documentList.stream()
                .filter(Document::isPrimary)
                .map(v -> new Workflow(v.getWorkflowId(), v.getName(), null))
                .collect(Collectors.toList());

        for (Workflow workflow : workflowList) {

            List<Document> middleList = new ArrayList<>();
            for (Document document : documentList) {

                if (document.getWorkflowId().equals(workflow.getId())) {
                    middleList.add(document);
                }

            }
            WorkflowStatus workflowStatus = setStatus(middleList.stream().map(Document::getDocType).collect(Collectors.toList()));
            workflow.setWorkflowStatus(workflowStatus);
        }

        return workflowList;
    }

    public WorkflowStatus setStatus(List<Enum<DocType>> docTypeList) {
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
