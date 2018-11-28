package hu.unideb.inf.notebookservice.service.interfaces;

import hu.unideb.inf.notebookservice.commons.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.service.domain.User;

import java.util.List;

public interface UserService {

    void register(RegistrationRequest registrationRequest);

    void saveUser(User user);

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();
}
