package com.luxoft.documentflow.docFlow.model.state;

public abstract class State {

    StateMachine stateMachine;

    public State(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    abstract void confirmation_of_receipt_of_the_primary_document_from_the_recipient();
    abstract void notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document();
    abstract void signing_of_the_primary_document_from_the_recipient();
    abstract void notification_of_receipt_of_the_signature_from_the_sender();
    abstract void notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender();
}
