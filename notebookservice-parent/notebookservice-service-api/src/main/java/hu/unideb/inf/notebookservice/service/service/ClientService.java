package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.ClientRequest;
import hu.unideb.inf.notebookservice.service.domain.Client;

import java.util.List;

public interface ClientService {

    void saveClient(ClientRequest clientRequest);

    Client findById(Long id);

    List<Client> findAll();
}
