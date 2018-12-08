package hu.unideb.inf.notebookservice.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.unideb.inf.notebookservice.commons.enumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private UserRole userRole;
}
