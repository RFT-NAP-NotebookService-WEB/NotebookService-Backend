package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.UserRequest;
import hu.unideb.inf.notebookservice.commons.response.SuccessResponse;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.interfaces.UserService;
import hu.unideb.inf.notebookservice.web.security.SecurityContextHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.path.UserPath.LOGIN_URL;
import static hu.unideb.inf.notebookservice.commons.path.UserPath.REGISTRATION_URL;
import static hu.unideb.inf.notebookservice.commons.path.UserPath.USERS_URL;
import static hu.unideb.inf.notebookservice.commons.path.UserPath.USER_ID_URL;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_USER;


@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @RequestMapping(value = REGISTRATION_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse> responseEntity(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(new SuccessResponse(userService.save(userRequest), null));
    }

    @GetMapping(LOGIN_URL)
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<SuccessResponse> authorizeUser() {
        User user = SecurityContextHolder.getUser();
        user = User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userRole(user.getUserRole())
                .build();
        return ResponseEntity.ok(new SuccessResponse(user, null));
    }

    @PutMapping(USER_ID_URL)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @GetMapping(USERS_URL)
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<User>> getAll() {
        List<User> allUser = userService.findAll();
        return ResponseEntity.accepted().body(allUser);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ViolationResponse> handleUserAlreadyExistsException(AlreadyExistsException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_USER)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleUserNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_USER)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(violationResponse);
    }
}
