package com.luxoft.documentflow.docFlow.service;

import com.luxoft.documentflow.docFlow.model.Workflow;
import com.luxoft.documentflow.docFlow.model.WorkflowState;
import com.luxoft.documentflow.docFlow.repository.WorkflowRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

public class WorkflowServiceCrudTest {
    private final WorkflowRepository repository = Mockito.mock(WorkflowRepository.class);
    private final WorkflowServiceCrud serviceCrud = new WorkflowServiceCrud(repository);

    @Test
    public void findWorkflowByIdTrue() {
        Long id = 1L;
        Workflow expected = new Workflow(id, "qweqwe", WorkflowState.NEW);
        Mockito.doReturn(Optional.of(expected)).when(repository).findById(id);

        Workflow actual = serviceCrud.findWorkflowById(id);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findWorkflowByIdThrow() {
        Long id = 2L;
        Mockito.doThrow(new NoSuchElementException()).when(repository).findById(id);

        Assertions.assertThrows(NoSuchElementException.class, () -> {
            serviceCrud.findWorkflowById(id);
        });
    }
}
