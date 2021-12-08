package com.luxoft.documentflow.docFlow.controller;

import com.luxoft.documentflow.docFlow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "v1/api/document")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping(value = "/sync")
    public Map<String, String> getDocumentCountByPriority() {
        return documentService.getDocumentCountByPriority();
    }
}
