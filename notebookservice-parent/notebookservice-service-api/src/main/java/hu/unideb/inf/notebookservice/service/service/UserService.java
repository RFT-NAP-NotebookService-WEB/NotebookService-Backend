package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.service.domain.User;

public interface UserService {

    void register(RegistrationRequest registrationRequest);

    void saveUser(User user);

    User findById(Long id);

    User findByUsername(String username);
}
