package hu.unideb.inf.notebookservice.backend.rest;

import hu.unideb.inf.notebookservice.backend.security.SecurityContextHolder;
import hu.unideb.inf.notebookservice.commons.path.LoginPath;
import hu.unideb.inf.notebookservice.commons.pojo.response.UserResponse;
import hu.unideb.inf.notebookservice.service.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationRestController {

    @GetMapping(LoginPath.LOGIN_URL)
    @PreAuthorize("isAuthenticated()")
    public UserResponse authorizeUser() {
        User user = SecurityContextHolder.getUser();
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userRole(user.getUserRole().toString())
                .build();
    }
}
