package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.service.converter.RegistrationRequestToUserConverter;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.service.RegistrationService;
import hu.unideb.inf.notebookservice.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegistrationService {

    private final UserService userService;

    private final RegistrationRequestToUserConverter converter;

    @Override
    public void register(RegistrationRequest registrationRequest) {

        User convertedUser = converter.convert(registrationRequest);

        userService.saveUser(convertedUser);
    }
}
