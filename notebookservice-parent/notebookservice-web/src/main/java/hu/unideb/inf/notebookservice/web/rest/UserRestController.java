package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.web.security.SecurityContextHolder;
import hu.unideb.inf.notebookservice.commons.pojo.path.LoginPath;
import hu.unideb.inf.notebookservice.commons.pojo.request.RegistrationRequest;
import hu.unideb.inf.notebookservice.commons.pojo.response.UserResponse;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static hu.unideb.inf.notebookservice.commons.pojo.path.RegistrationPath.REGISTRATION_URL;


@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @RequestMapping(value = REGISTRATION_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity responseEntity(@RequestBody RegistrationRequest registrationRequest) {
        ResponseEntity result;
        try {
            userService.register(registrationRequest);
            result = ResponseEntity.ok(registrationRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("400");
        }
        return result;
    }

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
