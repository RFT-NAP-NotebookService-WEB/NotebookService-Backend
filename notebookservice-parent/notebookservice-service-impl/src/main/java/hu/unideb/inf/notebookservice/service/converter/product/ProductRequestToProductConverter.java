package hu.unideb.inf.notebookservice.service.converter.product;

import hu.unideb.inf.notebookservice.commons.request.ProductRequest;
import hu.unideb.inf.notebookservice.service.domain.Product;
import hu.unideb.inf.notebookservice.service.interfaces.BrandService;
import hu.unideb.inf.notebookservice.service.interfaces.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRequestToProductConverter implements Converter<ProductRequest, Product> {

    private final BrandService brandService;
    private final ClientService clientService;

    @Override
    public Product convert(ProductRequest productRequest) {
        return Product.builder()
                .brand(brandService.findById(productRequest.getBrandId()))
                .type(productRequest.getType())
                .description(productRequest.getDescription())
                .client(clientService.findById(productRequest.getClientId()))
                .build();
    }

    public Product convert(Long id, ProductRequest productRequest) {
        return Product.builder()
                .id(id)
                .brand(brandService.findById(productRequest.getBrandId()))
                .type(productRequest.getType())
                .description(productRequest.getDescription())
                .client(clientService.findById(productRequest.getClientId()))
                .build();
    }

}
