package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import hu.unideb.inf.notebookservice.persistence.repository.UserRepository;
import hu.unideb.inf.notebookservice.service.converter.user.RegistrationRequestToUserConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserEntityToUserConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserToUserEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityToUserConverter toDomain;
    private final UserToUserEntityConverter toEntity;
    private final UserRepository userRepository;
    private final RegistrationRequestToUserConverter converter;

    @Override
    public void register(RegistrationRequest registrationRequest) {

        log.info(">> Convert to Domain >> [registrationRequest:{}]", registrationRequest);
        User convertedUser = converter.convert(registrationRequest);

        log.info(">> Register request >> [convertedUser:{}]", convertedUser);
        saveUser(convertedUser);
    }

    @Override
    public void saveUser(User user) {

        log.info(">> Convert to Entity >> [user:{}]", user);
        UserEntity userEntity = toEntity.convert(user);

        log.info(">> Saving to Database >> [userEntity:{}]", userEntity);
        userRepository.save(userEntity);
    }

    @Override
    public User findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        UserEntity userEntity = userRepository.getOne(id);

        log.info(">> Response >> [userEntity:{}]", userEntity);
        return toDomain.convert(userEntity);
    }

    @Override
    public User findByUsername(String username) {
        log.info(">> Searching in Database >> [username:{}]", username);
        UserEntity userEntity = userRepository.findByUsername(username);

        log.info(">> Response >> [userEntity:{}]", userEntity);
        return toDomain.convert(userEntity);
    }
}
