package com.luxoft.documentflow.docFlow.model;

public enum WorkflowState {
    NEW(null),
    WAITING_FOR_RECEIPT_CONFIRMATION("confirmation_of_receipt_of_the_primary_document_from_the_recipient"),
    SENT("notification_of_receipt_of_confirmation_of_receipt_of_the_primary_document"),
    SIGNED("signing_of_the_primary_document_from_the_recipient"),
    SENDING_CONFIRMATION("notification_of_receipt_of_the_signature_from_the_sender"),
    FINISHED("notification_of_receipt_of_the_notification_of_receipt_of_the_signature_from_the_sender");

    private final String documentName;

    WorkflowState(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentName() {
        return documentName;
    }
}
