package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.ClientRequest;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.domain.Client;
import hu.unideb.inf.notebookservice.service.interfaces.ClientService;
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

import static hu.unideb.inf.notebookservice.commons.path.ClientPath.CLIENTS_URL;
import static hu.unideb.inf.notebookservice.commons.path.ClientPath.CLIENT_ID_URL;
import static hu.unideb.inf.notebookservice.commons.path.ClientPath.CLIENT_URL;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_CLIENT;

@RestController
@RequiredArgsConstructor
public class ClientRestController {

    private final ClientService clientService;

    @RequestMapping(value = CLIENT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> responseEntity(@RequestBody ClientRequest clientRequest) {
        return ResponseEntity.ok(clientService.save(clientRequest));
    }

    @GetMapping(path = CLIENT_ID_URL)
    public ResponseEntity<Client> getClientByID(@PathVariable Long id) {
        Client foundClient = clientService.findById(id);
        return ResponseEntity.accepted().body(foundClient);
    }

    @RequestMapping(value = CLIENT_ID_URL, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientRequest client) {
        return ResponseEntity.ok(clientService.update(id, client));
    }

    @GetMapping(path = CLIENTS_URL)
    public ResponseEntity<List<Client>> getAllClient() {
        List<Client> allClient = clientService.findAll();
        return ResponseEntity.accepted().body(allClient);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ViolationResponse> handleClientAlreadyExistsException(AlreadyExistsException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_CLIENT)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleClientNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_CLIENT)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(violationResponse);
    }
}
