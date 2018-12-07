package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.ModificationRequest;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import hu.unideb.inf.notebookservice.service.interfaces.ModificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.path.ModificationPath.MODIFICATIONS_URL;
import static hu.unideb.inf.notebookservice.commons.path.ModificationPath.MODIFICATION_ID_URL;
import static hu.unideb.inf.notebookservice.commons.path.ModificationPath.MODIFICATION_URL;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_MODIFICATION;

@RestController
@RequiredArgsConstructor
public class ModificationRestController {

    private final ModificationService modificationService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = MODIFICATION_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Modification> responseEntity(@RequestBody ModificationRequest modificationRequest) {
        return ResponseEntity.ok(modificationService.save(modificationRequest));
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MODIFICATION_ID_URL)
    public ResponseEntity<Modification> getModificationByID(@PathVariable Long id) {
        Modification foundModification = modificationService.findById(id);
        return ResponseEntity.accepted().body(foundModification);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MODIFICATIONS_URL)
    public ResponseEntity<List<Modification>> getAllModification() {
        List<Modification> allModification = modificationService.findAll();
        return ResponseEntity.accepted().body(allModification);
    }

    @RequestMapping(value = MODIFICATION_ID_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Modification> updateClient(@PathVariable Long id, @RequestBody ModificationRequest modification) {
        return ResponseEntity.ok(modificationService.update(id, modification));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ViolationResponse> handleModificationAlreadyExistsException(AlreadyExistsException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_MODIFICATION)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleModificationNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_MODIFICATION)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(violationResponse);
    }
}
