package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.WorkflowState;
import com.luxoft.documentflow.docFlow.model.state.StateMachine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StateMachineTest {

    @Test
    public void changeStateSent() {
        WorkflowState oldState = WorkflowState.NEW;
        List<DocType> docTypeList = Arrays.asList(DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document,
                DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient);

        WorkflowState expectedState = WorkflowState.SENT;

        WorkflowState actualState = new StateMachine().changeState(docTypeList, oldState);

        Assertions.assertEquals(expectedState, actualState);
    }

    @Test
    public void changeStateNew() {
        WorkflowState oldState = WorkflowState.NEW;
        List<DocType> docTypeList = Arrays.asList(DocType.signing_of_the_primary_document_from_the_recipient,
                DocType.notification_of_receipt_of_the_signature_from_the_sender);

        WorkflowState expectedState = WorkflowState.NEW;

        WorkflowState actualState = new StateMachine().changeState(docTypeList, oldState);

        Assertions.assertEquals(expectedState, actualState);
    }

    @Test
    public void changeStateFinished() {
        WorkflowState oldState = WorkflowState.SENT;
        List<DocType> docTypeList = Arrays.asList(DocType.signing_of_the_primary_document_from_the_recipient,
                DocType.notification_of_receipt_of_the_signature_from_the_sender,
                DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender,
                DocType.signing_of_the_primary_document_from_the_recipient,
                DocType.notification_of_receipt_of_the_signature_from_the_sender);

        WorkflowState expectedState = WorkflowState.FINISHED;

        WorkflowState actualState = new StateMachine().changeState(docTypeList, oldState);

        Assertions.assertEquals(expectedState, actualState);
    }
}
