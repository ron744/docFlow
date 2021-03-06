package com.luxoft.documentflow.docFlow.repository;

import com.luxoft.documentflow.docFlow.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {
}
