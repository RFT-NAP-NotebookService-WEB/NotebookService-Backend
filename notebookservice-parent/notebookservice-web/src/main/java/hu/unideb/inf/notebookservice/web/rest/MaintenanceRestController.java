package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.path.MaintenancePath.MAINTENANCE_URL;
import static hu.unideb.inf.notebookservice.commons.path.MaintenancePath.MAINTENANCES_URL;
import static hu.unideb.inf.notebookservice.commons.path.MaintenancePath.MAINTENANCE_ID_URL;

@RestController
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final MaintenanceService maintenanceService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = MAINTENANCE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MAINTENANCE_ID_URL)
    public ResponseEntity<?> getMaintenanceByID(@PathVariable Long id) {
        Maintenance foundMaintenance = maintenanceService.findById(id);
        return ResponseEntity.accepted().body(foundMaintenance);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MAINTENANCES_URL)
    public ResponseEntity<?> getAllMaintenance() {
        List<Maintenance> allMaintenance = maintenanceService.findAll();
        return ResponseEntity.accepted().body(allMaintenance);
    }
}
