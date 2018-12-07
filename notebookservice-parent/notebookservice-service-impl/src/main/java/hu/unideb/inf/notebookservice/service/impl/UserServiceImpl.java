package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.UserRequest;
import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import hu.unideb.inf.notebookservice.persistence.repository.UserRepository;
import hu.unideb.inf.notebookservice.service.converter.user.UserEntityListToUserListConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserEntityToUserConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserRequestToUserConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserToUserEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ALREADY_EXISTS_EXCEPTION;
import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ID_NOT_FOUND_EXCEPTION;
import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.NAME_NOT_FOUND_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityListToUserListConverter toDomainList;
    private final UserEntityToUserConverter toDomain;
    private final UserToUserEntityConverter toEntity;
    private final UserRepository repository;
    private final UserRequestToUserConverter fromRequest;

    @Override
    public User save(UserRequest userRequest) {
        Optional<UserEntity> entity = repository.findByUsername(userRequest.getUsername());
        if (entity.isPresent()) {
            throw new AlreadyExistsException(String.format(ALREADY_EXISTS_EXCEPTION, userRequest.getUsername()));
        }

        log.info(">> Convert to Domain >> [userRequest:{}]", userRequest);
        User user = fromRequest.convert(userRequest);

        log.info(">> Convert to Entity >> [user:{}]", user);
        UserEntity userEntity = toEntity.convert(user);

        log.info(">> Saving to Database >> [userEntity:{}]", userEntity);
        UserEntity savedUser = repository.save(userEntity);

        log.info(">> Response >> [User:{}]", savedUser);
        return toDomain.convert(savedUser);
    }

    @Override
    public User update(User user) {
        User found = findById(user.getId());
        found = user;

        log.info(">> Convert to Entity >> [found:{}]", found);
        UserEntity converted = toEntity.convert(found);

        log.info(">> Saving to Database >> [converted:{}]", converted);
        UserEntity savedUser = repository.save(converted);

        log.info(">> Response >> [User:{}]", savedUser);
        return toDomain.convert(savedUser);
    }

    @Override
    public User findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        Optional<UserEntity> userEntity = repository.findById(id);

        log.info(">> Response >> [userEntity:{}]", userEntity);
        return toDomain.convert(
                userEntity.orElseThrow(
                        () -> new NotFoundException(
                                String.format(ID_NOT_FOUND_EXCEPTION, id))));
    }

    @Override
    public User findByUsername(String username) {
        log.info(">> Searching in Database >> [username:{}]", username);
        Optional<UserEntity> userEntity = repository.findByUsername(username);

        log.info(">> Response >> [userEntity:{}]", userEntity);
        return toDomain.convert(
                userEntity.orElseThrow(
                        () -> new NotFoundException(
                                String.format(NAME_NOT_FOUND_EXCEPTION, username))));
    }

    @Override
    public List<User> findAll() {
        log.info(">> Finding all User <<");
        List<UserEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain <<");
        List<User> userList = toDomainList.convert(entityList);

        log.info(">> Response <<");
        return userList;
    }
}
