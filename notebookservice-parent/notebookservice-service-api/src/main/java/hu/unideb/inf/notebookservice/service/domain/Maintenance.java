package hu.unideb.inf.notebookservice.service.domain;

import hu.unideb.inf.notebookservice.commons.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private String fault;
    private User user;
    private Product product;
    private List<Modification> modifications;
}
