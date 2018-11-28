package hu.unideb.inf.notebookservice.commons.request;

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
public class MaintenanceRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;
    private String fault;
    private Long userId;
    private Long productId;
    private List<Long> modificationsId;
}
