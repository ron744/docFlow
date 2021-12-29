package com.luxoft.documentflow.docFlow.controller;

import com.luxoft.documentflow.docFlow.model.Document;
import com.luxoft.documentflow.docFlow.service.DocumentService;
import com.luxoft.documentflow.docFlow.service.DocumentServiceCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/document")
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentServiceCrud documentServiceCrud;

    @Autowired
    public DocumentController(DocumentService documentService, DocumentServiceCrud documentServiceCrud) {
        this.documentService = documentService;
        this.documentServiceCrud = documentServiceCrud;
    }

    @GetMapping(value = "/sync")
    public Map<String, String> getDocumentCountByPriority() {
        return documentService.getDocumentCountByPriority();
    }

    @GetMapping(value = "/getById")
    public Document getDocumentById(@RequestParam Long id) {
        return documentServiceCrud.getById(id);
    }

}
