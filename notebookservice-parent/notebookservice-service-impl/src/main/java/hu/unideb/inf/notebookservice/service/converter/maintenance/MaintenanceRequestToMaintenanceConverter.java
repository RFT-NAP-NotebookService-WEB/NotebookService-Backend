package hu.unideb.inf.notebookservice.service.converter.maintenance;

import hu.unideb.inf.notebookservice.commons.pojo.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationIdListToModificationListConverter;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.service.ProductService;
import hu.unideb.inf.notebookservice.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceRequestToMaintenanceConverter implements Converter<MaintenanceRequest, Maintenance> {

    private final UserService userService;
    private final ProductService productService;
    private final ModificationIdListToModificationListConverter idToList;

    @Override
    public Maintenance convert(MaintenanceRequest maintenanceRequest) {
        return Maintenance.builder()
                .product(productService.findById(maintenanceRequest.getProductId()))
                .status(maintenanceRequest.getStatus())
                .startDate(maintenanceRequest.getStartDate())
                .endDate(maintenanceRequest.getEndDate())
                .fault(maintenanceRequest.getFault())
                .modifications(idToList.convert(maintenanceRequest.getModificationsId()))
                .user(userService.findById(maintenanceRequest.getUserId()))
                .build();
    }
}
