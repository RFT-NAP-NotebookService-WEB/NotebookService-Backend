package hu.unideb.inf.notebookservice.service.converter.brand;

import hu.unideb.inf.notebookservice.persistence.entity.BrandEntity;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrandEntityListToBrandListConverter implements Converter<List<BrandEntity>, List<Brand>> {

    private final BrandEntityToBrandConverter toDomain;

    @Override
    public List<Brand> convert(List<BrandEntity> brandEntities) {
        return brandEntities.stream().map(toDomain::convert).collect(Collectors.toList());
    }
}
