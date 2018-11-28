package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.request.ClientRequest;
import hu.unideb.inf.notebookservice.persistence.entity.ClientEntity;
import hu.unideb.inf.notebookservice.persistence.repository.ClientRepository;
import hu.unideb.inf.notebookservice.service.converter.client.ClientEntityListToClientConverter;
import hu.unideb.inf.notebookservice.service.converter.client.ClientEntityToClientConverter;
import hu.unideb.inf.notebookservice.service.converter.client.ClientRequestToClientConverter;
import hu.unideb.inf.notebookservice.service.converter.client.ClientToClientEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Client;
import hu.unideb.inf.notebookservice.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientRequestToClientConverter fromRequest;
    private final ClientToClientEntityConverter toEntity;
    private final ClientEntityToClientConverter toDomain;
    private final ClientEntityListToClientConverter toDomainList;

    @Override
    public void saveClient(ClientRequest clientRequest) {

        log.info(">> Converting Request >> [clientRequest:{}]", clientRequest);
        Client client = fromRequest.convert(clientRequest);

        log.info(">> Converting Domain >> [client:{}]", client);
        ClientEntity converted = toEntity.convert(client);

        assert converted != null;
        log.info(">> Saving Entity >> [converted:{}]", converted);
        repository.save(converted);
    }

    @Override
    public Client findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        ClientEntity foundClient = repository.getOne(id);

        log.info(">> Converting to Domain >> [foundClient:{}]", foundClient);
        Client convertedClient = toDomain.convert(foundClient);

        log.info(">> Response >> [convertedClient:{}]", convertedClient);
        return convertedClient;
    }

    @Override
    public List<Client> findAll() {

        log.info(">> Finding all Brand <<");
        List<ClientEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain >> [entityList:{}]", entityList);
        List<Client> clientList = toDomainList.convert(entityList);

        log.info(">> Response >> [clientList:{}]", clientList);
        return clientList;
    }
}
