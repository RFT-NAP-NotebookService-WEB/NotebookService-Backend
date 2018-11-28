package hu.unideb.inf.notebookservice.commons.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
