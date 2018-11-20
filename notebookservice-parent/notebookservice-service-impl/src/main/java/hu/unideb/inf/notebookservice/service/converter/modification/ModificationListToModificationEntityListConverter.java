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
public class ModificationListToModificationEntityListConverter implements Converter<List<Modification>, List<ModificationEntity>> {

    private final ModificationToModificationEntityConverter toModificationEntityConverter;

    @Override
    public List<ModificationEntity> convert(List<Modification> modifications) {
        return modifications.stream().map(toModificationEntityConverter::convert).collect(Collectors.toList());
    }
}
