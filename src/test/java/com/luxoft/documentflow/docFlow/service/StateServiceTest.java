package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.model.WorkflowState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateServiceTest {

    private final WorkflowServiceCrud serviceCrud = Mockito.mock(WorkflowServiceCrud.class);
    private final StateService stateService = new StateService(serviceCrud);

    @BeforeEach
    public void setUp() {
        Mockito.doReturn(new Workflow(1L, "primaryDocument1", WorkflowState.WAITING_FOR_RECEIPT_CONFIRMATION)).when(serviceCrud).findWorkflowById(1L);
        Mockito.doReturn(new Workflow(2L, "primaryDocument2", WorkflowState.NEW)).when(serviceCrud).findWorkflowById(2L);
        Mockito.doReturn(new Workflow(3L, "primaryDocument3", WorkflowState.NEW)).when(serviceCrud).findWorkflowById(3L);
    }

    @Test
    public void generateWorkflowStatusSIGNED() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document2", 1L, DocType.signing_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document3", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
        documentList.add(new Document("primaryDocument1", 1L, null, true));

        List<Workflow> workflowList = stateService.processing(documentList);

        assertEquals(1, workflowList.size());
        assertEquals("primaryDocument1", workflowList.get(0).getName());
        assertEquals(WorkflowState.SIGNED, workflowList.get(0).getWorkflowState());
    }

    @Test
    public void generateWorkflowStatusSENDING_CONFIRMATION() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document("document1", 1L, DocType.signing_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document2", 1L, DocType.notification_of_receipt_of_the_signature_from_the_sender, false));
        documentList.add(new Document("primaryDocument1", 1L, null, true));
        documentList.add(new Document("document3", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));

        List<Workflow> workflowList = stateService.processing(documentList);

        assertEquals(1, workflowList.size());
        assertEquals("primaryDocument1", workflowList.get(0).getName());
        assertEquals(WorkflowState.SENDING_CONFIRMATION, workflowList.get(0).getWorkflowState());
    }

    @Test
    public void generateWorkflowStatusFINISHED() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document100", 1L, DocType.notification_of_receipt_of_the_signature_from_the_sender, false));
        documentList.add(new Document("document2", 1L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));
        documentList.add(new Document("document3", 1L, DocType.signing_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("primaryDocument1", 1L, null, true));
        documentList.add(new Document("document4", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));

        List<Workflow> workflowList = stateService.processing(documentList);

        assertEquals(1, workflowList.size());
        assertEquals("primaryDocument1", workflowList.get(0).getName());
        assertEquals(WorkflowState.FINISHED, workflowList.get(0).getWorkflowState());
    }

    @Test
    public void generateWorkflowStatusSENT() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document("document1", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document2", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("primaryDocument1", 1L, null, true));
        documentList.add(new Document("document3", 1L, DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document, false));
        documentList.add(new Document("document400", 2L, DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender, false));

        List<Workflow> workflowList = stateService.processing(documentList);

        assertEquals(1, workflowList.size());
        assertEquals("primaryDocument1", workflowList.get(0).getName());
        assertEquals(WorkflowState.SENT, workflowList.get(0).getWorkflowState());
    }

    @Test
    public void generate2WorkflowsStatusWAITING_FOR_RECEIPT_CONFIRMATION() {
        List<Document> documentList = new ArrayList<>();
        documentList.add(new Document("document1", 3L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document2", 1L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("primaryDocument3", 3L, null, true));
        documentList.add(new Document("primaryDocument2", 2L, null, true));
        documentList.add(new Document("document3", 3L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));
        documentList.add(new Document("document400", 2L, DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient, false));

        List<Workflow> workflowList = stateService.processing(documentList);

        assertEquals(2, workflowList.size());
        assertEquals("primaryDocument2", workflowList.get(0).getName());
        assertEquals("primaryDocument3", workflowList.get(1).getName());
        assertEquals(WorkflowState.WAITING_FOR_RECEIPT_CONFIRMATION, workflowList.get(0).getWorkflowState());
        assertEquals(WorkflowState.WAITING_FOR_RECEIPT_CONFIRMATION, workflowList.get(1).getWorkflowState());
    }
}
