package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
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
import java.util.Optional;

import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ALREADY_EXISTS_EXCEPTION;
import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ID_NOT_FOUND_EXCEPTION;

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
    public Modification save(ModificationRequest modificationRequest) {

        Optional<ModificationEntity> entity = repository.findByName(modificationRequest.getName());
        if (entity.isPresent()) {
            throw new AlreadyExistsException(String.format(ALREADY_EXISTS_EXCEPTION, modificationRequest.getName()));
        }

        log.info(">> Converting Request >> [modificationRequest:{}]", modificationRequest);
        Modification modification = fromRequest.convert(modificationRequest);

        log.info(">> Converting Domain >> [modification:{}]", modification);
        ModificationEntity converted = toEntity.convert(modification);

        log.info(">> Saving Entity >> [converted:{}]", converted);
        return toDomain.convert(repository.save(converted));
    }

    @Override
    public Modification update(Long id, ModificationRequest modification) {
        findById(id);
        Modification newModification = fromRequest.convert(id, modification);

        log.info(">> Converting Domain >> [newModification:{}]", newModification);
        ModificationEntity converted = toEntity.convert(newModification);

        log.info(">> Saving Entity >> [converted:{}]", converted);
        ModificationEntity saved = repository.save(converted);

        log.info(">> Response >> [saved:{}]", saved);
        return toDomain.convert(saved);
    }

    @Override
    public Modification findById(Long id) {
        log.info(">> Searching in Database >> [id:{}]", id);
        Optional<ModificationEntity> foundModification = repository.findById(id);

        log.info(">> Converting to Domain >> [foundModification:{}]", foundModification);
        Modification convertedModification = toDomain.convert(
                foundModification.orElseThrow(
                        () -> new NotFoundException(
                                String.format(ID_NOT_FOUND_EXCEPTION, id))));

        log.info(">> Response >> [convertedModification:{}]", convertedModification);
        return convertedModification;
    }

    @Override
    public List<Modification> findAll() {

        log.info(">> Finding all Brand <<");
        List<ModificationEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain <<");
        return toDomainList.convert(entityList);
    }
}
