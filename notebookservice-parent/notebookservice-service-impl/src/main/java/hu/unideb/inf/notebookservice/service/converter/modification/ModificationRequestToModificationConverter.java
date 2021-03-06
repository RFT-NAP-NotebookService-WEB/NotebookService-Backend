package hu.unideb.inf.notebookservice.service.converter.modification;

import hu.unideb.inf.notebookservice.commons.request.ModificationRequest;
import hu.unideb.inf.notebookservice.service.domain.Modification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ModificationRequestToModificationConverter implements Converter<ModificationRequest, Modification> {

    @Override
    public Modification convert(ModificationRequest modificationRequest) {
        return Modification.builder()
                .name(modificationRequest.getName())
                .price(modificationRequest.getPrice())
                .build();
    }

    public Modification convert(Long id, ModificationRequest modificationRequest) {
        return Modification.builder()
                .id(id)
                .name(modificationRequest.getName())
                .price(modificationRequest.getPrice())
                .build();
    }
}
