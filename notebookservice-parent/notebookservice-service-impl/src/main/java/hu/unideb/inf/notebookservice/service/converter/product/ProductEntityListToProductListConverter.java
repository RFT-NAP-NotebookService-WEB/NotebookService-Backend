package hu.unideb.inf.notebookservice.service.converter.product;

import hu.unideb.inf.notebookservice.persistence.entity.ProductEntity;
import hu.unideb.inf.notebookservice.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductEntityListToProductListConverter implements Converter<List<ProductEntity>, List<Product>> {

    private final ProductEntityToProductConverter toProductConverter;

    @Override
    public List<Product> convert(List<ProductEntity> productEntities) {
        return productEntities.stream().map(toProductConverter::convert).collect(Collectors.toList());
    }
}
