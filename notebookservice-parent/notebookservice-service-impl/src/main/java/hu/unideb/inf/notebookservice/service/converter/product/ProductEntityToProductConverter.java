package hu.unideb.inf.notebookservice.service.converter.product;

import hu.unideb.inf.notebookservice.persistence.entity.ProductEntity;
import hu.unideb.inf.notebookservice.service.converter.brand.BrandEntityToBrandConverter;
import hu.unideb.inf.notebookservice.service.converter.client.ClientEntityToClientConverter;
import hu.unideb.inf.notebookservice.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEntityToProductConverter implements Converter<ProductEntity, Product> {

    private final BrandEntityToBrandConverter toBrandConverter;
    private final ClientEntityToClientConverter toClientConverter;

    @Override
    public Product convert(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .brand(toBrandConverter.convert(productEntity.getBrand()))
                .type(productEntity.getType())
                .description(productEntity.getDescription())
                .client(toClientConverter.convert(productEntity.getClient()))
                .build();
    }
}
