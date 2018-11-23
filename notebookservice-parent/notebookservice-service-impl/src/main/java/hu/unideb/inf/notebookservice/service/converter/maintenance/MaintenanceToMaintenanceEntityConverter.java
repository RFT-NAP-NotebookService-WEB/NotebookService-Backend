package hu.unideb.inf.notebookservice.service.converter.maintenance;

import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import hu.unideb.inf.notebookservice.service.converter.modification.ModificationListToModificationEntityListConverter;
import hu.unideb.inf.notebookservice.service.converter.product.ProductToProductEntityConverter;
import hu.unideb.inf.notebookservice.service.converter.user.UserToUserEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceToMaintenanceEntityConverter implements Converter<Maintenance, MaintenanceEntity> {

    private final UserToUserEntityConverter toUserEntityConverter;
    private final ProductToProductEntityConverter toProductEntityConverter;
    private final ModificationListToModificationEntityListConverter toModificationEntityListConverter;

    @Override
    public MaintenanceEntity convert(Maintenance maintenance) {
        return MaintenanceEntity.builder()
                .id(maintenance.getId())
                .product(toProductEntityConverter.convert(maintenance.getProduct()))
                .status(maintenance.getStatus())
                .startDate(maintenance.getStartDate())
                .endDate(maintenance.getEndDate())
                .fault(maintenance.getFault())
                .modifications(toModificationEntityListConverter.convert(maintenance.getModifications()))
                .user(toUserEntityConverter.convert(maintenance.getUser()))
                .build();
    }
}
