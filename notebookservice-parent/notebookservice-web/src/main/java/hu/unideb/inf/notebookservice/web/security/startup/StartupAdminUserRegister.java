package hu.unideb.inf.notebookservice.web.security.startup;

import hu.unideb.inf.notebookservice.commons.request.UserRequest;
import hu.unideb.inf.notebookservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
class StartupAdminUserRegister {

    private final UserService userService;
    private final AdministratorUserDetailsHolder detailsHolder;

    @PostConstruct
    public void registerAdmin() {
        if (!userService.containsAny()) {
            UserRequest adminUser = UserRequest.builder()
                    .username(detailsHolder.getUsername())
                    .password(detailsHolder.getPassword())
                    .passwordConfirm(detailsHolder.getPassword())
                    .userRole(detailsHolder.getUserRole())
                    .build();
            userService.save(adminUser);
            log.info("Admin user registred");
        } else {
            log.info("There is at least one existing user, no admin registered");
        }
    }
}
