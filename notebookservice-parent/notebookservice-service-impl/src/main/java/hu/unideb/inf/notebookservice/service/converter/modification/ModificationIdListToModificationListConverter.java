package hu.unideb.inf.notebookservice.service.converter.modification;

import hu.unideb.inf.notebookservice.service.domain.Modification;
import hu.unideb.inf.notebookservice.service.service.ModificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModificationIdListToModificationListConverter implements Converter<List<Long>, List<Modification>> {

    private final ModificationService modificationService;

    @Override
    public List<Modification> convert(List<Long> longs) {
        return longs.stream().map(modificationService::findById).collect(Collectors.toList());
    }
}
