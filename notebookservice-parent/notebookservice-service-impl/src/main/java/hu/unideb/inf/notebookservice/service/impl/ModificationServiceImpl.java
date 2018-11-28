package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.request.ModificationRequest;
import hu.unideb.inf.notebookservice.persistence.entity.ModificationEntity;
import hu.unideb.inf.notebookservice.persistence.repository.ModificationRepository;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationEntityListToModificationListConverter;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationEntityToModificationConverter;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationRequestToModificationConverter;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationToModificationEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import hu.unideb.inf.notebookservice.service.interfaces.ModificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModificationServiceImpl implements ModificationService {

    private final ModificationEntityToModificationConverter toDomain;
    private final ModificationToModificationEntityConverter toEntity;
    private final ModificationRequestToModificationConverter fromRequest;
    private final ModificationEntityListToModificationListConverter toDomainList;
    private final ModificationRepository repository;

    @Override
    public void saveModification(ModificationRequest modificationRequest) {

        log.info(">> Converting Request >> [modificationRequest:{}]", modificationRequest);
        Modification modification = fromRequest.convert(modificationRequest);

        log.info(">> Converting Domain >> [modification:{}]", modification);
        ModificationEntity converted = toEntity.convert(modification);

        assert converted != null;
        log.info(">> Saving Entity >> [converted:{}]", converted);
        repository.save(converted);
    }

    @Override
    public Modification findById(Long id) {
        log.info(">> Searching in Database >> [id:{}]", id);
        ModificationEntity foundModification = repository.getOne(id);

        log.info(">> Converting to Domain >> [foundModification:{}]", foundModification);
        Modification convertedModification = toDomain.convert(foundModification);

        log.info(">> Response >> [convertedModification:{}]", convertedModification);
        return convertedModification;
    }

    @Override
    public List<Modification> findAll() {

        log.info(">> Finding all Brand <<");
        List<ModificationEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain >> [entityList:{}]", entityList);
        List<Modification> modificationList = toDomainList.convert(entityList);

        log.info(">> Response >> [modificationList:{}]", modificationList);
        return modificationList;
    }
}
