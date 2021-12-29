package com.luxoft.documentflow.docFlow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentStack {
    private Long workflowId;
    private List<Document> documents;
}
