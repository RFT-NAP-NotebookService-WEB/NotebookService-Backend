package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.path.MaintenancePath.MAINTENANCES_URL;
import static hu.unideb.inf.notebookservice.commons.path.MaintenancePath.MAINTENANCE_ID_URL;
import static hu.unideb.inf.notebookservice.commons.path.MaintenancePath.MAINTENANCE_URL;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_MAINTENANCE;

@RestController
@RequiredArgsConstructor
public class MaintenanceRestController {

    private final MaintenanceService maintenanceService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = MAINTENANCE_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Maintenance> responseEntity(@RequestBody MaintenanceRequest maintenanceRequest) {
        return ResponseEntity.ok(maintenanceService.save(maintenanceRequest));
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MAINTENANCE_ID_URL)
    public ResponseEntity<Maintenance> getMaintenanceByID(@PathVariable Long id) {
        Maintenance foundMaintenance = maintenanceService.findById(id);
        return ResponseEntity.accepted().body(foundMaintenance);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MAINTENANCES_URL)
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        List<Maintenance> allMaintenance = maintenanceService.findAll();
        return ResponseEntity.accepted().body(allMaintenance);
    }

    @PutMapping(MAINTENANCE_ID_URL)
    public ResponseEntity<Maintenance> updateClient(@PathVariable Long id, @RequestBody MaintenanceRequest maintenance) {
        return ResponseEntity.ok(maintenanceService.update(id, maintenance));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ViolationResponse> handleMaintenanceAlreadyExistsException(AlreadyExistsException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_MAINTENANCE)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleMaintenanceNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_MAINTENANCE)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(violationResponse);
    }
}
