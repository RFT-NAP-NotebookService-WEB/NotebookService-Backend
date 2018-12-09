package hu.unideb.inf.notebookservice.web.security;

import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.converter.user.UserToUserEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.User;
import hu.unideb.inf.notebookservice.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_USER;

@Service
@RequiredArgsConstructor
public class NotebookServiceUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserToUserEntityConverter userConverter;

    @Override
    public NotebookServiceUserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        return new NotebookServiceUserDetails(user);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleUserNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_USER)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }
}
