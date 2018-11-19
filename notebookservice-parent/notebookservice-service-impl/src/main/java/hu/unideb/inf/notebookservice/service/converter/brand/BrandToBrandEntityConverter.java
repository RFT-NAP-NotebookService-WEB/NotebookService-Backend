package hu.unideb.inf.notebookservice.service.converter.brand;

import hu.unideb.inf.notebookservice.persistence.entity.BrandEntity;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BrandToBrandEntityConverter implements Converter<Brand, BrandEntity> {

    @Override
    public BrandEntity convert(Brand brand) {
        return BrandEntity.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
