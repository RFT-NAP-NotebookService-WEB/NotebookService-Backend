package hu.unideb.inf.notebookservice.service.converter.maintenance;

import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationEntityListToModificationListConverter;
import hu.unideb.inf.notebookservice.service.converter.product.ProductEntityToProductConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserEntityToUserConverter;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceEntityToMaintenanceConverter implements Converter<MaintenanceEntity, Maintenance> {

    private final UserEntityToUserConverter toUserConverter;
    private final ProductEntityToProductConverter toProductConverter;
    private final ModificationEntityListToModificationListConverter toModificationListConverter;

    @Override
    public Maintenance convert(MaintenanceEntity maintenanceEntity) {
        return Maintenance.builder()
                .id(maintenanceEntity.getId())
                .product(toProductConverter.convert(maintenanceEntity.getProduct()))
                .status(maintenanceEntity.getStatus())
                .startDate(maintenanceEntity.getStartDate())
                .endDate(maintenanceEntity.getEndDate())
                .fault(maintenanceEntity.getFault())
                .modifications(toModificationListConverter.convert(maintenanceEntity.getModifications()))
                .user(toUserConverter.convert(maintenanceEntity.getUser()))
                .build();
    }
}
