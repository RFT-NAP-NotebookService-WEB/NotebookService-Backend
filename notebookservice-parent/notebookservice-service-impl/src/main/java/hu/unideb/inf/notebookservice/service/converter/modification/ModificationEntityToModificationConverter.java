package hu.unideb.inf.notebookservice.service.converter.modification;

import hu.unideb.inf.notebookservice.persistence.entity.ModificationEntity;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModificationEntityToModificationConverter implements Converter<ModificationEntity, Modification> {

    @Override
    public Modification convert(ModificationEntity modificationEntity) {
        return Modification.builder()
                .id(modificationEntity.getId())
                .name(modificationEntity.getName())
                .price(modificationEntity.getPrice())
                .build();
    }
}
