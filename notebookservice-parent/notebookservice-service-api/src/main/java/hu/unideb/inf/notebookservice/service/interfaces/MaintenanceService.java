package hu.unideb.inf.notebookservice.service.interfaces;

import hu.unideb.inf.notebookservice.commons.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;

import java.util.List;

public interface MaintenanceService extends BaseService<Maintenance, MaintenanceRequest> {

    List<Maintenance> allDone();
    List<Maintenance> notDone();
}
