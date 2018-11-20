package hu.unideb.inf.notebookservice.service.converter.modification;

import hu.unideb.inf.notebookservice.persistence.entity.ModificationEntity;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModificationToModificationEntityConverter implements Converter<Modification, ModificationEntity> {

    @Override
    public ModificationEntity convert(Modification modification) {
        return ModificationEntity.builder()
                .id(modification.getId())
                .name(modification.getName())
                .price(modification.getPrice())
                .build();
    }
}
