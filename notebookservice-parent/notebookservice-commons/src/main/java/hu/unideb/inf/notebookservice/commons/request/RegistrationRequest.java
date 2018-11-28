package hu.unideb.inf.notebookservice.commons.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    private String username;
    private String password;
    private String passwordConfirm;
    private String userRole;
}
