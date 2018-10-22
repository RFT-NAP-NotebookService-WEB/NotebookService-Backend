package hu.unideb.inf.notebookservice.service.converter;

import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.service.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestToUserConverter implements Converter<RegistrationRequest, User> {

    @Override
    public User convert(RegistrationRequest registrationRequest) {
        return User.builder()
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .passwordConfirm(registrationRequest.getPasswordConfirm())
                .userRole(registrationRequest.getUserRole())
                .build();
    }
}
