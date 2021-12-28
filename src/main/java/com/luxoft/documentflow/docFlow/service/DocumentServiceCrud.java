package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DocumentServiceCrud {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceCrud(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document add(Document document) {
        return documentRepository.save(document);
    }

    public Document getById(Long id) {
        return documentRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
