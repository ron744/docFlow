package com.luxoft.documentflow.docFlow.repository;

import com.luxoft.documentflow.docFlow.model.Workflow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends CrudRepository<Workflow, Long> {
}
