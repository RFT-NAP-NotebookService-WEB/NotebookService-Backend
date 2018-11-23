package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.pojo.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import hu.unideb.inf.notebookservice.persistence.repository.MaintenanceRepository;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceEntityListToMaintenanceListConverter;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceEntityToMaintenanceConverter;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceRequestToMaintenanceConverter;
import hu.unideb.inf.notebookservice.service.converter.maintenance.MaintenanceToMaintenanceEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceEntityListToMaintenanceListConverter toDomainList;
    private final MaintenanceRequestToMaintenanceConverter fromRequest;
    private final MaintenanceToMaintenanceEntityConverter toEntity;
    private final MaintenanceEntityToMaintenanceConverter toDomain;
    private final MaintenanceRepository repository;

    @Override
    public void saveMaintenance(MaintenanceRequest maintenanceRequest) {
        log.info(">> Converting Request >> [maintenanceRequest:{}]", maintenanceRequest);
        Maintenance maintenance = fromRequest.convert(maintenanceRequest);

        log.info(">> Converting Domain >> [maintenance:{}]", maintenance);
        MaintenanceEntity converted = toEntity.convert(maintenance);

        assert converted != null;
        log.info(">> Saving Entity >> [converted:{}]", converted);
        repository.save(converted);
    }

    @Override
    public Maintenance findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        MaintenanceEntity foundMaintenance = repository.getOne(id);

        log.info(">> Converting to Domain >> [foundMaintenance:{}]", foundMaintenance);
        Maintenance converterMaintenance = toDomain.convert(foundMaintenance);

        log.info(">> Response >> [converterMaintenance:{}]", converterMaintenance);
        return converterMaintenance;
    }

    @Override
    public List<Maintenance> findAll() {
        log.info(">> Finding all Maintenance <<");
        List<MaintenanceEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain >> [entityList:{}]", entityList);
        List<Maintenance> maintenanceList = toDomainList.convert(entityList);

        log.info(">> Response >> [maintenanceList:{}]", maintenanceList);
        return maintenanceList;
    }
}
