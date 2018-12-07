package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.ProductRequest;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.domain.Product;
import hu.unideb.inf.notebookservice.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static hu.unideb.inf.notebookservice.commons.path.ProductPath.PRODUCTS_URL;
import static hu.unideb.inf.notebookservice.commons.path.ProductPath.PRODUCT_ID_URL;
import static hu.unideb.inf.notebookservice.commons.path.ProductPath.PRODUCT_URL;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_PRODUCT;

@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = PRODUCT_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> responseEntity(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.save(productRequest));
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = PRODUCT_ID_URL)
    public ResponseEntity<Product> getProductByID(@PathVariable Long id) {
        Product foundProduct = productService.findById(id);
        return ResponseEntity.accepted().body(foundProduct);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = PRODUCTS_URL)
    public ResponseEntity<List<Product>> getAllProduct() {
        List<Product> allProduct = productService.findAll();
        return ResponseEntity.accepted().body(allProduct);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ViolationResponse> handleProductAlreadyExistsException(AlreadyExistsException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_PRODUCT)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleProductNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_PRODUCT)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(violationResponse);
    }
}
