package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.request.ModificationRequest;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import hu.unideb.inf.notebookservice.service.interfaces.ModificationService;
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

import static hu.unideb.inf.notebookservice.commons.path.ModificationPath.MODIFICATION_ADD;
import static hu.unideb.inf.notebookservice.commons.path.ModificationPath.MODIFICATION_GET_ALL;
import static hu.unideb.inf.notebookservice.commons.path.ModificationPath.MODIFICATION_GET_ONE;

@RestController
@RequiredArgsConstructor
public class ModificationRestController {

    private final ModificationService modificationService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = MODIFICATION_ADD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> responseEntity(@RequestBody ModificationRequest modificationRequest) {
        ResponseEntity result;
        try {
            modificationService.saveModification(modificationRequest);
            result = ResponseEntity.ok(modificationRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
        }
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MODIFICATION_GET_ONE)
    public ResponseEntity<?> getModificationByID(@PathVariable Long id) {
        Modification foundModification = modificationService.findById(id);
        return ResponseEntity.accepted().body(foundModification);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = MODIFICATION_GET_ALL)
    public ResponseEntity<?> getAllModification() {
        List<Modification> allModification = modificationService.findAll();
        return ResponseEntity.accepted().body(allModification);
    }
}
