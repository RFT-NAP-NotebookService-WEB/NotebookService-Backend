package hu.unideb.inf.notebookservice.service.converter.user;

import hu.unideb.inf.notebookservice.persistence.entity.UserEntity;
import hu.unideb.inf.notebookservice.service.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserEntityConverter implements Converter<User, UserEntity> {

    @Override
    public UserEntity convert(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .build();
    }
}
