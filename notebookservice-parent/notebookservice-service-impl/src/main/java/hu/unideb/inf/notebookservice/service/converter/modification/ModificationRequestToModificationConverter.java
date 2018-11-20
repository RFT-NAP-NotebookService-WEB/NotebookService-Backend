package hu.unideb.inf.notebookservice.service.converter.modification;

import hu.unideb.inf.notebookservice.commons.pojo.request.ModificationRequest;
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
}
