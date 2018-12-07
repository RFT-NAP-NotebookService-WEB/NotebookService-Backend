package hu.unideb.inf.notebookservice.commons.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private List<Error> errors;

    public void addError(String message) {
        this.errors.add(new Error(message));
    }
}
