package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Map<String, String> getDocumentCountByPriority() {
        return new HashMap<>();
    }
}
