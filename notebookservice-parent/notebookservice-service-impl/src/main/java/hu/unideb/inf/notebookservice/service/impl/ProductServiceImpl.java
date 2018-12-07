package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.ProductRequest;
import hu.unideb.inf.notebookservice.persistence.entity.ProductEntity;
import hu.unideb.inf.notebookservice.persistence.repository.ProductRepository;
import hu.unideb.inf.notebookservice.service.converter.product.ProductEntityListToProductListConverter;
import hu.unideb.inf.notebookservice.service.converter.product.ProductEntityToProductConverter;
import hu.unideb.inf.notebookservice.service.converter.product.ProductRequestToProductConverter;
import hu.unideb.inf.notebookservice.service.converter.product.ProductToProductEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Product;
import hu.unideb.inf.notebookservice.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ID_NOT_FOUND_EXCEPTION;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductRequestToProductConverter fromRequest;
    private final ProductToProductEntityConverter toEntity;
    private final ProductEntityToProductConverter toDomain;
    private final ProductEntityListToProductListConverter toDomainList;

    @Override
    public Product save(ProductRequest productRequest) {
        log.info(">> Converting Request >> [productRequest:{}]", productRequest);
        Product product = fromRequest.convert(productRequest);

        log.info(">> Converting Domain >> [product:{}]", product);
        ProductEntity converted = toEntity.convert(product);

        log.info(">> Saving Entity >> [converted:{}]", converted);
        return toDomain.convert(repository.save(converted));
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        log.info(">> Searching in Database >> [id:{}]", id);
        Optional<ProductEntity> foundProduct = repository.findById(id);

        log.info(">> Converting to Domain >> [foundProduct:{}]", foundProduct);
        Product convertedProduct = toDomain.convert(
                foundProduct.orElseThrow(
                        () -> new NotFoundException(
                                String.format(ID_NOT_FOUND_EXCEPTION, id))));

        log.info(">> Response >> [convertedProduct:{}]", convertedProduct);
        return convertedProduct;
    }

    @Override
    public List<Product> findAll() {
        log.info(">> Finding all Product <<");
        List<ProductEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain <<");
        List<Product> productList = toDomainList.convert(entityList);

        log.info(">> Response <<");
        return productList;
    }
}
