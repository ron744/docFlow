package com.luxoft.documentflow.docFlow.model.state;

import com.luxoft.documentflow.docFlow.model.DocType;
import com.luxoft.documentflow.docFlow.model.WorkflowState;
import lombok.Data;

import java.util.Collection;

@Data
public class StateMachine {
    private WorkflowState state;

    public WorkflowState changeState(Collection<DocType> docTypeList, WorkflowState oldState) {
        state = oldState;

        boolean changeState = false;

        do {
            for (int i  = 0; i < docTypeList.size(); i++) {
                if (docTypeList.contains(DocType.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender) && state.equals(WorkflowState.SENDING_CONFIRMATION)) {

                    state = WorkflowState.FINISHED;
                    changeState = true;

                } else if (docTypeList.contains(DocType.notification_of_receipt_of_the_signature_from_the_sender) && state.equals(WorkflowState.SIGNED)) {

                    state = WorkflowState.SENDING_CONFIRMATION;
                    changeState = true;

                } else if (docTypeList.contains(DocType.signing_of_the_primary_document_from_the_recipient) && state.equals(WorkflowState.SENT)) {

                    state = WorkflowState.SIGNED;
                    changeState = true;

                } else if (docTypeList.contains(DocType.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document) && state.equals(WorkflowState.WAITING_FOR_RECEIPT_CONFIRMATION)) {

                    state = WorkflowState.SENT;
                    changeState = true;

                } else if (docTypeList.contains(DocType.confirmation_of_receipt_of_the_primary_document_from_the_recipient) && state.equals(WorkflowState.NEW)) {

                    state = WorkflowState.WAITING_FOR_RECEIPT_CONFIRMATION;
                    changeState = true;

                }

            }


            changeState = !changeState;

        } while (changeState);


        return state;
    }
}
