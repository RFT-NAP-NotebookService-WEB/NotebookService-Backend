package hu.unideb.inf.notebookservice.service.interfaces;

import hu.unideb.inf.notebookservice.commons.request.ProductRequest;
import hu.unideb.inf.notebookservice.service.domain.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(ProductRequest productRequest);

    Product findById(Long id);

    List<Product> findAll();
}
