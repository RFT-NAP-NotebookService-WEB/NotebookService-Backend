package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
import hu.unideb.inf.notebookservice.commons.request.BrandRequest;
import hu.unideb.inf.notebookservice.commons.violation.Violation;
import hu.unideb.inf.notebookservice.commons.violation.ViolationResponse;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import hu.unideb.inf.notebookservice.service.interfaces.BrandService;
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

import static hu.unideb.inf.notebookservice.commons.path.BrandPath.BRANDS_URL;
import static hu.unideb.inf.notebookservice.commons.path.BrandPath.BRAND_ID_URL;
import static hu.unideb.inf.notebookservice.commons.path.BrandPath.BRAND_URL;
import static hu.unideb.inf.notebookservice.commons.table.TableName.TABLE_NAME_BRAND;

@RestController
@RequiredArgsConstructor
public class BrandRestController {

    private final BrandService brandService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = BRAND_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Brand> responseEntity(@RequestBody BrandRequest brandRequest) {
        return ResponseEntity.ok(brandService.save(brandRequest));
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = BRAND_ID_URL)
    public ResponseEntity<Brand> getBrandByID(@PathVariable Long id) {
        Brand foundBrand = brandService.findById(id);
        return ResponseEntity.accepted().body(foundBrand);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = BRANDS_URL)
    public ResponseEntity<List<Brand>> getAllBrand() {
        List<Brand> allBrand = brandService.findAll();
        return ResponseEntity.accepted().body(allBrand);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ViolationResponse> handleBrandAlreadyExistsException(AlreadyExistsException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_BRAND)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(violationResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ViolationResponse> handleBrandNotFoundException(NotFoundException exception) {
        ViolationResponse violationResponse = ViolationResponse.builder()
                .errors(Collections.singletonList(Violation.builder()
                        .field(TABLE_NAME_BRAND)
                        .violationMessage(exception.getMessage())
                        .build()))
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(violationResponse);
    }
}
