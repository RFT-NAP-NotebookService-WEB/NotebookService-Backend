package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.request.ClientRequest;
import hu.unideb.inf.notebookservice.service.domain.Client;
import hu.unideb.inf.notebookservice.service.interfaces.ClientService;
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

import static hu.unideb.inf.notebookservice.commons.path.ClientPath.CLIENT_URL;
import static hu.unideb.inf.notebookservice.commons.path.ClientPath.CLIENTS_URL;
import static hu.unideb.inf.notebookservice.commons.path.ClientPath.CLIENT_ID_URL;

@RestController
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = CLIENT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> responseEntity(@RequestBody ClientRequest clientRequest) {
        ResponseEntity result;
        try {
            clientService.saveClient(clientRequest);
            result = ResponseEntity.ok(clientRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
        }
        return result;
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = CLIENT_ID_URL)
    public ResponseEntity<?> getClientByID(@PathVariable Long id) {
        Client foundClient = clientService.findById(id);
        return ResponseEntity.accepted().body(foundClient);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = CLIENTS_URL)
    public ResponseEntity<?> getAllClient() {
        List<Client> allClient = clientService.findAll();
        return ResponseEntity.accepted().body(allClient);
    }
}
