package com.luxoft.documentflow.docFlow.model.state;

public class NewState extends State {

    public NewState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public void confirmation_of_receipt_of_the_primary_document_from_the_recipient() {

    }

    @Override
    public void notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document() {
        stateMachine.changeState(new SentState(stateMachine));
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
