package hu.unideb.inf.notebookservice.service.converter;

import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import hu.unideb.inf.notebookservice.service.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserConverter implements Converter<UserEntity, User> {

    @Override
    public User convert(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .userRole(userEntity.getUserRole())
                .build();
    }
}
