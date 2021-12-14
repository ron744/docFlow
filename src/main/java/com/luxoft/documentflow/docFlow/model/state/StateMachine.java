package com.luxoft.documentflow.docFlow.model.state;

import lombok.Data;

import java.util.List;

@Data
public class StateMachine {
    private State state;
    private List<String> stateList;

    public StateMachine(List<String> stateList) {
        this.state = new NewState(this);
        this.stateList = stateList;

        for (int i = 0; i < stateList.size(); i++) {
            if (stateList.get(i).equals("confirmation_of_receipt_of_the_primary_document_from_the_recipient")) {

            }
        }
    }

    public void confirmation_of_receipt_of_the_primary_document_from_the_recipient() {
        state.confirmation_of_receipt_of_the_primary_document_from_the_recipient();
    }

    public void notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document() {
        state.notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document();
    }

    public void signing_of_the_primary_document_from_the_recipient() {
        state.signing_of_the_primary_document_from_the_recipient();
    }

    public void notification_of_receipt_of_the_signature_from_the_sender() {
        state.notification_of_receipt_of_the_signature_from_the_sender();
    }

    public void notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender() {
        state.notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender();
    }

    public void changeState(State state) {
        this.state = state;
    }
}
