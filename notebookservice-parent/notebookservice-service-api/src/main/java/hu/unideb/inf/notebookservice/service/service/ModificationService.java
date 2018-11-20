package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.ModificationRequest;
import hu.unideb.inf.notebookservice.service.domain.Modification;

import java.util.List;

public interface ModificationService {

    void saveModification(ModificationRequest modificationRequest);

    Modification findById(Long id);

    List<Modification> findAll();
}
