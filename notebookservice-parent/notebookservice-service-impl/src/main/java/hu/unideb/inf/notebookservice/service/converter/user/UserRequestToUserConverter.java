package hu.unideb.inf.notebookservice.service.converter.user;

import hu.unideb.inf.notebookservice.commons.enumeration.UserRole;
import hu.unideb.inf.notebookservice.commons.request.UserRequest;
import hu.unideb.inf.notebookservice.service.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRequestToUserConverter implements Converter<UserRequest, User> {

    private static final BCryptPasswordEncoder PW_ENCODE = new BCryptPasswordEncoder();

    @Override
    public User convert(UserRequest userRequest) {
        return User.builder()
                .username(userRequest.getUsername())
                .password(PW_ENCODE.encode(userRequest.getPassword()))
                .passwordConfirm(PW_ENCODE.encode(userRequest.getPasswordConfirm()))
                .userRole(UserRole.valueOf(userRequest.getUserRole()))
                .build();
    }

    public User convert(Long id, UserRequest userRequest) {
        return User.builder()
                .id(id)
                .username(userRequest.getUsername())
                .password(PW_ENCODE.encode(userRequest.getPassword()))
                .passwordConfirm(PW_ENCODE.encode(userRequest.getPasswordConfirm()))
                .userRole(UserRole.valueOf(userRequest.getUserRole()))
                .build();
    }
}
