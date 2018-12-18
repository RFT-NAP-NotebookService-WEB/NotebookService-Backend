package hu.unideb.inf.notebookservice.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static hu.unideb.inf.notebookservice.commons.path.HealthPath.HEALTH_URL;

@RestController
public class HealthRestController {

    @GetMapping(HEALTH_URL)
    public ResponseEntity getHealth() {
        return ResponseEntity.ok("OK");
    }
}
