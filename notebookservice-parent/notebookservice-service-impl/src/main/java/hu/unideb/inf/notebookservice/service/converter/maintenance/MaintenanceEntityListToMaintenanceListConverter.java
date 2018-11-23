package hu.unideb.inf.notebookservice.service.converter.maintenance;

import hu.unideb.inf.notebookservice.persistence.entity.MaintenanceEntity;
import hu.unideb.inf.notebookservice.service.domain.Maintenance;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MaintenanceEntityListToMaintenanceListConverter implements Converter<List<MaintenanceEntity>, List<Maintenance>> {

    private final MaintenanceEntityToMaintenanceConverter toMaintenanceConverter;

    @Override
    public List<Maintenance> convert(List<MaintenanceEntity> maintenanceEntities) {
        return maintenanceEntities.stream().map(toMaintenanceConverter::convert).collect(Collectors.toList());
    }
}
