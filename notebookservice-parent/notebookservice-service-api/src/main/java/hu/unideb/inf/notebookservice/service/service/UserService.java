package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.service.domain.User;

public interface UserService {

    User saveUser(User user);

    User findByUsername(String username);
}
