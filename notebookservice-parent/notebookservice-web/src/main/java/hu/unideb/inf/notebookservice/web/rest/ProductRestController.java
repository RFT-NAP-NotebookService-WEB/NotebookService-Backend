package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.pojo.request.ProductRequest;
import hu.unideb.inf.notebookservice.service.domain.Product;
import hu.unideb.inf.notebookservice.service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.pojo.path.ProductPath.PRODUCT_ADD;
import static hu.unideb.inf.notebookservice.commons.pojo.path.ProductPath.PRODUCT_GET_ALL;
import static hu.unideb.inf.notebookservice.commons.pojo.path.ProductPath.PRODUCT_GET_ONE;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = PRODUCT_ADD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> responseEntity(@RequestBody ProductRequest productRequest) {
        ResponseEntity result;
        try {
            productService.saveProduct(productRequest);
            result = ResponseEntity.ok(productRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
        }
        return result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = PRODUCT_GET_ONE)
    public ResponseEntity<?> getProductByID(@PathVariable Long id) {
        Product foundProduct = productService.findById(id);
        return ResponseEntity.accepted().body(foundProduct);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = PRODUCT_GET_ALL)
    public ResponseEntity<?> getAllProduct() {
        List<Product> allProduct = productService.findAll();
        return ResponseEntity.accepted().body(allProduct);
    }
}
