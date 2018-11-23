package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;

import java.util.List;

public interface MaintenanceService {

    void saveMaintenance(MaintenanceRequest maintenanceRequest);

    Maintenance findById(Long id);

    List<Maintenance> findAll();
}
