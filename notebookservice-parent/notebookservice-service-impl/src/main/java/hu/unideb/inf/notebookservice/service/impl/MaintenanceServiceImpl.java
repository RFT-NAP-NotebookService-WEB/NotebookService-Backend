package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import hu.unideb.inf.notebookservice.persistence.repository.MaintenanceRepository;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceEntityListToMaintenanceListConverter;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceEntityToMaintenanceConverter;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceRequestToMaintenanceConverter;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceToMaintenanceEntityConverter;
import hu.unideb.inf.notebookservice.service.converter.product.ProductToProductEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.interfaces.MaintenanceService;
import hu.unideb.inf.notebookservice.service.interfaces.ProductService;
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
public class MaintenanceServiceImpl implements MaintenanceService {

    private final ProductToProductEntityConverter productEntityConverter;
    private final MaintenanceEntityListToMaintenanceListConverter toDomainList;
    private final MaintenanceRequestToMaintenanceConverter fromRequest;
    private final MaintenanceToMaintenanceEntityConverter toEntity;
    private final MaintenanceEntityToMaintenanceConverter toDomain;
    private final MaintenanceRepository repository;
    private final ProductService productService;

    @Override
    public Maintenance save(MaintenanceRequest maintenanceRequest) {

        Optional<MaintenanceEntity> entity = repository.findByProduct(
                productEntityConverter.convert(
                        productService.findById(maintenanceRequest.getProductId())));
        if (entity.isPresent()) {
            throw new AlreadyExistsException(String.format(ALREADY_EXISTS_EXCEPTION, maintenanceRequest));
        }

        log.info(">> Converting Request >> [maintenanceRequest:{}]", maintenanceRequest);
        Maintenance maintenance = fromRequest.convert(maintenanceRequest);

        log.info(">> Converting Domain >> [maintenance:{}]", maintenance);
        MaintenanceEntity converted = toEntity.convert(maintenance);

        log.info(">> Saving Entity >> [converted:{}]", converted);
        return toDomain.convert(repository.save(converted));
    }

    @Override
    public Maintenance update(Long id, MaintenanceRequest maintenance) {
        findById(id);
        Maintenance newMaintenance = fromRequest.convert(id, maintenance);

        log.info(">> Converting Domain >> [newMaintenance:{}]", newMaintenance);
        MaintenanceEntity converted = toEntity.convert(newMaintenance);

        log.info(">> Saving Entity >> [converted:{}]", converted);
        MaintenanceEntity saved = repository.save(converted);

        log.info(">> Response >> [saved:{}]", saved);
        return toDomain.convert(saved);
    }

    @Override
    public Maintenance findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        Optional<MaintenanceEntity> foundMaintenance = repository.findById(id);

        log.info(">> Converting to Domain >> [foundMaintenance:{}]", foundMaintenance);
        Maintenance converterMaintenance = toDomain.convert(
                foundMaintenance.orElseThrow(
                        () -> new NotFoundException(
                                String.format(ID_NOT_FOUND_EXCEPTION, id))));

        log.info(">> Response >> [converterMaintenance:{}]", converterMaintenance);
        return converterMaintenance;
    }

    @Override
    public List<Maintenance> findAll() {
        log.info(">> Finding all Maintenance <<");
        List<MaintenanceEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain <<");
        return toDomainList.convert(entityList);
    }
}
