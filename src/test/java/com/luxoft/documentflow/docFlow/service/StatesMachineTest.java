package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.model.WorkflowStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatesMachineTest {

    @Test
    public void generateWorkflowStatusSENT() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document2", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document3", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document4", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("primaryDocument1", 1L, null, true));

        List<Workflow> workflowsWithOldState = new ArrayList<>();

//        List<Workflow> workflowsWithNewState = new StatesMachine().processing(documentList, workflowsWithOldState);
//
//        assertEquals(1, workflowsWithNewState.size());
//        assertEquals("primaryDocument1", workflowsWithNewState.get(0).getName());
//        assertEquals(WorkflowStatus.NEW, workflowsWithNewState.get(0).getWorkflowStatus());
    }

//    @Test
//    public void generateWorkflowStatusWAITINGFORSIGN() {
//        List<Document> documentList = new ArrayList<>();
//        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document2", 1L, DocType.signing_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document3", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
//        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
//        documentList.add(new Document("primaryDocument1", 1L, null, true));
//
//        List<Workflow> workflowList = new StatesMachine().processing(documentList);
//
//        assertEquals(1, workflowList.size());
//        assertEquals("primaryDocument1", workflowList.get(0).getName());
//        assertEquals(WorkflowStatus.WAITING_FOR_SIGN, workflowList.get(0).getWorkflowStatus());
//    }
//
//    @Test
//    public void generateWorkflowStatusSIGNED() {
//        List<Document> documentList = new ArrayList<>();
//        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document2", 1L, DocType.notification_of_receipt_of_the_signature_from_the_sender, false));
//        documentList.add(new Document("primaryDocument1", 1L, null, true));
//        documentList.add(new Document("document3", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
//        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
//
//        List<Workflow> workflowList = new StatesMachine().processing(documentList);
//
//        assertEquals(1, workflowList.size());
//        assertEquals("primaryDocument1", workflowList.get(0).getName());
//        assertEquals(WorkflowStatus.SIGNED, workflowList.get(0).getWorkflowStatus());
//    }
//
//    @Test
//    public void generateWorkflowStatusFINISHED() {
//        List<Document> documentList = new ArrayList<>();
//        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document100", 10L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document2", 1L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
//        documentList.add(new Document("document3", 1L, DocType.signing_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("primaryDocument1", 1L, null, true));
//        documentList.add(new Document("document4", 1L, DocType.signing_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
//
//        List<Workflow> workflowList = new StatesMachine().processing(documentList);
//
//        assertEquals(1, workflowList.size());
//        assertEquals("primaryDocument1", workflowList.get(0).getName());
//        assertEquals(WorkflowStatus.FINISHED, workflowList.get(0).getWorkflowStatus());
//    }
//
//    @Test
//    public void generateWorkflowStatusWAITING_FOR_RECEIPT_CONFIRMATION() {
//        List<Document> documentList = new ArrayList<>();
//        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document2", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("primaryDocument1", 1L, null, true));
//        documentList.add(new Document("document3", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
//        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
//
//        List<Workflow> workflowList = new StatesMachine().processing(documentList);
//
//        assertEquals(1, workflowList.size());
//        assertEquals("primaryDocument1", workflowList.get(0).getName());
//        assertEquals(WorkflowStatus.WAITING_FOR_RECEIPT_CONFIRMATION, workflowList.get(0).getWorkflowStatus());
//    }
//
//    @Test
//    public void generate2WorkflowsStatusNew() {
//        List<Document> documentList = new ArrayList<>();
//        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document2", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("primaryDocument1", 1L, null, true));
//        documentList.add(new Document("primaryDocument2", 2L, null, true));
//        documentList.add(new Document("document3", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//        documentList.add(new Document("document400", 2L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
//
//        List<Workflow> workflowList = new StatesMachine().processing(documentList);
//
//        assertEquals(2, workflowList.size());
//        assertEquals("primaryDocument1", workflowList.get(0).getName());
//        assertEquals("primaryDocument2", workflowList.get(1).getName());
//        assertEquals(WorkflowStatus.NEW, workflowList.get(0).getWorkflowStatus());
//        assertEquals(WorkflowStatus.NEW, workflowList.get(1).getWorkflowStatus());
//    }
}
