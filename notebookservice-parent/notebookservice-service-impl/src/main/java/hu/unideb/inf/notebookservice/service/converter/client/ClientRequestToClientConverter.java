package hu.unideb.inf.notebookservice.service.converter.client;

import hu.unideb.inf.notebookservice.commons.pojo.request.ClientRequest;
import hu.unideb.inf.notebookservice.service.domain.Client;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestToClientConverter implements Converter<ClientRequest, Client> {

    @Override
    public Client convert(ClientRequest clientRequest) {
        return Client.builder()
                .firstName(clientRequest.getFirstName())
                .lastName(clientRequest.getLastName())
                .email(clientRequest.getEmail())
                .phone(clientRequest.getPhone())
                .build();
    }
}
