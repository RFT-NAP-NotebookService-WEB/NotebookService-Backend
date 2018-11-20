package hu.unideb.inf.notebookservice.service.converter.modification;

import hu.unideb.inf.notebookservice.persistence.entity.ModificationEntity;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModificationEntityListToModificationListConverter implements Converter<List<ModificationEntity>, List<Modification>> {

    private final ModificationEntityToModificationConverter toModificationConverter;

    @Override
    public List<Modification> convert(List<ModificationEntity> modificationEntities) {
        return modificationEntities.stream().map(toModificationConverter::convert).collect(Collectors.toList());
    }
}
