package com.luxoft.documentflow.docFlow.model.state;

public class SentState extends State {

    public SentState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public void confirmation_of_receipt_of_the_primary_document_from_the_recipient() {

    }

    @Override
    public void notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document() {

    }

    @Override
    public void signing_of_the_primary_document_from_the_recipient() {

    }

    @Override
    public void notification_of_receipt_of_the_signature_from_the_sender() {

    }

    @Override
    public void notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender() {

    }
}
