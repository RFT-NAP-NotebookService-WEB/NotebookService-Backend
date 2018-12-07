package hu.unideb.inf.notebookservice.commons.violation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Violation {

    private String field;

    private String violationMessage;
}
