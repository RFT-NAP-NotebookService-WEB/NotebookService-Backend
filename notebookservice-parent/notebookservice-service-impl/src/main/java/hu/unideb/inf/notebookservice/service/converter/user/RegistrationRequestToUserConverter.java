package hu.unideb.inf.notebookservice.service.converter.user;

import hu.unideb.inf.notebookservice.commons.pojo.enumeration.UserRole;
import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.service.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationRequestToUserConverter implements Converter<RegistrationRequest, User> {

    private static final BCryptPasswordEncoder PW_ENCODE = new BCryptPasswordEncoder();

    @Override
    public User convert(RegistrationRequest registrationRequest) {
        return User.builder()
                .username(registrationRequest.getUsername())
                .password(PW_ENCODE.encode(registrationRequest.getPassword()))
                .passwordConfirm(PW_ENCODE.encode(registrationRequest.getPasswordConfirm()))
                .userRole(UserRole.valueOf(registrationRequest.getUserRole()))
                .build();
    }
}
