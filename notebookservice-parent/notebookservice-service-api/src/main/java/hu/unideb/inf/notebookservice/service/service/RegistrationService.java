package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.service.domain.User;

public interface RegistrationService {

    User register(RegistrationRequest registrationRequest) throws Exception;
}