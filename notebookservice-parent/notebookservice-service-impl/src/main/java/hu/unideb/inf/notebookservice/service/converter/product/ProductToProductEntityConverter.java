package hu.unideb.inf.notebookservice.service.converter.product;

import hu.unideb.inf.notebookservice.persistence.entity.ProductEntity;
import hu.unideb.inf.notebookservice.service.converter.brand.BrandToBrandEntityConverter;
import hu.unideb.inf.notebookservice.service.converter.client.ClientToClientEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductToProductEntityConverter implements Converter<Product, ProductEntity> {

    private final BrandToBrandEntityConverter toBrandEntityConverter;
    private final ClientToClientEntityConverter toClientEntityConverter;

    @Override
    public ProductEntity convert(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .brand(toBrandEntityConverter.convert(product.getBrand()))
                .type(product.getType())
                .description(product.getDescription())
                .client(toClientEntityConverter.convert(product.getClient()))
                .build();
    }
}
