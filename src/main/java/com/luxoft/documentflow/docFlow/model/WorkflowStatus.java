package com.luxoft.documentflow.docFlow.model;

public enum WorkflowStatus {
    NEW,
    WAITING_FOR_RECEIPT_CONFIRMATION,
    SENT,
    WAITING_FOR_SIGN,
    SIGNED,
    SENDING_CONFIRMATION,
    FINISHED
}
