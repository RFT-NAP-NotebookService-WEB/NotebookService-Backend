package hu.unideb.inf.notebookservice.service.converter.maintenance;

import hu.unideb.inf.notebookservice.commons.request.MaintenanceRequest;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationIdListToModificationListConverter;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import hu.unideb.inf.notebookservice.service.interfaces.ProductService;
import hu.unideb.inf.notebookservice.service.interfaces.UserService;
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

    public Maintenance convert(Long id, MaintenanceRequest maintenanceRequest) {
        return Maintenance.builder()
                .id(id)
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
