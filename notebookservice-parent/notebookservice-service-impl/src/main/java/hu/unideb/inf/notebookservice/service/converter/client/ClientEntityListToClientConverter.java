package hu.unideb.inf.notebookservice.service.converter.client;

import hu.unideb.inf.notebookservice.persistence.entity.ClientEntity;
import hu.unideb.inf.notebookservice.service.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientEntityListToClientConverter implements Converter<List<ClientEntity>, List<Client>> {

    private final ClientEntityToClientConverter toClientConverter;

    @Override
    public List<Client> convert(List<ClientEntity> clientEntities) {
        return clientEntities.stream().map(toClientConverter::convert).collect(Collectors.toList());
    }
}
