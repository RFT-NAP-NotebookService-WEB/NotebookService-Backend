package hu.unideb.inf.notebookservice.service.interfaces;

import hu.unideb.inf.notebookservice.commons.request.UserRequest;
import hu.unideb.inf.notebookservice.service.domain.User;

public interface UserService extends BaseService<User, UserRequest> {

    User findByUsername(String username);
}
