package hu.unideb.inf.notebookservice.service.interfaces;

import hu.unideb.inf.notebookservice.commons.request.ClientRequest;
import hu.unideb.inf.notebookservice.service.domain.Client;

import java.util.List;

public interface ClientService {

    void saveClient(ClientRequest clientRequest);

    Client findById(Long id);

    List<Client> findAll();
}
