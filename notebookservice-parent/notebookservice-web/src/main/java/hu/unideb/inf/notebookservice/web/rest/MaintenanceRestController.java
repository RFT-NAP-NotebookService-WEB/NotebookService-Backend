package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.pojo.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.path.MaintenancePath.MAINTENANCE_ADD;
import static hu.unideb.inf.notebookservice.commons.pojo.path.MaintenancePath.MAINTENANCE_GET_ALL;
import static hu.unideb.inf.notebookservice.commons.pojo.path.MaintenancePath.MAINTENANCE_GET_ONE;

@RestController
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final MaintenanceService maintenanceService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = MAINTENANCE_ADD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> responseEntity(@RequestBody MaintenanceRequest maintenanceRequest) {
        ResponseEntity result;
        try {
            maintenanceService.saveMaintenance(maintenanceRequest);
            result = ResponseEntity.ok(maintenanceRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
        }
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MAINTENANCE_GET_ONE)
    public ResponseEntity<?> getMaintenanceByID(@PathVariable Long id) {
        Maintenance foundMaintenance = maintenanceService.findById(id);
        return ResponseEntity.accepted().body(foundMaintenance);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MAINTENANCE_GET_ALL)
    public ResponseEntity<?> getAllMaintenance() {
        List<Maintenance> allMaintenance = maintenanceService.findAll();
        return ResponseEntity.accepted().body(allMaintenance);
    }
}
