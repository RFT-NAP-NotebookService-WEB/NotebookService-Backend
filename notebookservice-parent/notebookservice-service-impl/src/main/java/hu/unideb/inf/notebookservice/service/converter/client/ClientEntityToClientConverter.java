package hu.unideb.inf.notebookservice.service.converter.client;

import hu.unideb.inf.notebookservice.persistence.entity.ClientEntity;
import hu.unideb.inf.notebookservice.service.domain.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityToClientConverter implements Converter<ClientEntity, Client> {

    @Override
    public Client convert(ClientEntity clientEntity) {
        return Client.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .email(clientEntity.getEmail())
                .phone(clientEntity.getPhone())
                .build();
    }
}
