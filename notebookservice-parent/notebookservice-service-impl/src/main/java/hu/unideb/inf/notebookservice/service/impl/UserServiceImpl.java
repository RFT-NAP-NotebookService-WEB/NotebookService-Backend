package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import hu.unideb.inf.notebookservice.persistence.repository.UserRepository;
import hu.unideb.inf.notebookservice.service.converter.UserEntityToUserConverter;
import hu.unideb.inf.notebookservice.service.converter.UserToUserEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jboss.logging.ParameterConverter;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityToUserConverter toDomain;

    private final UserToUserEntityConverter toEntity;

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {

        UserEntity userEntity = toEntity.convert(user);

        UserEntity saved = userRepository.save(userEntity);

        toDomain.convert(saved);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        return toDomain.convert(userEntity);
    }
}
