package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.ProductRequest;
import hu.unideb.inf.notebookservice.service.domain.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductRequest productRequest);

    Product findById(Long id);

    List<Product> findAll();
}
