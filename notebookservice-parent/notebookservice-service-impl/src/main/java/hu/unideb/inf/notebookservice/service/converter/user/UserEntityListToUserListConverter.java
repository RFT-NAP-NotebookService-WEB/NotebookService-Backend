package hu.unideb.inf.notebookservice.service.converter.user;

import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import hu.unideb.inf.notebookservice.service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserEntityListToUserListConverter implements Converter<List<UserEntity>, List<User>> {

    private final UserEntityToUserConverter userConverter;

    @Override
    public List<User> convert(List<UserEntity> userEntities) {
        return userEntities.stream().map(userConverter::convert).collect(Collectors.toList());
    }
}
