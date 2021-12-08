package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceCrud {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceCrud(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public ResponseEntity<Document> add(Document document) {
        return ResponseEntity.ok(documentRepository.save(document));
    }

    public ResponseEntity<Document> getById(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(NoSuchFieldError::new);
        return ResponseEntity.ok(document);
    }
}
