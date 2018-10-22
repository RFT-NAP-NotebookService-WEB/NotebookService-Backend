package hu.unideb.inf.notebookservice.service.domain;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
public class User {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String passwordConfirm;

    private String userRole;
}
