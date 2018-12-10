package hu.unideb.inf.notebookservice.web.security.startup;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Getter
@Component
public class AdministratorUserDetailsHolder {

    @Value("${admin-user.username}")
    private String username;

    @Value("${admin-user.password}")
    private String password;

    @Value("${admin-user.userrole}")
    private String userRole;
}
