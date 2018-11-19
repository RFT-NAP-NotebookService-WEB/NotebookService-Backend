package hu.unideb.inf.notebookservice.service.converter.brand;

import hu.unideb.inf.notebookservice.persistence.entity.BrandEntity;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BrandEntityToBrandConverter implements Converter<BrandEntity, Brand> {

    @Override
    public Brand convert(BrandEntity brandEntity) {
        return Brand.builder()
                .id(brandEntity.getId())
                .name(brandEntity.getName())
                .build();
    }
}
