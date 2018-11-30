package hu.unideb.inf.notebookservice.web.rest;

import hu.unideb.inf.notebookservice.commons.request.BrandRequest;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import hu.unideb.inf.notebookservice.service.interfaces.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static hu.unideb.inf.notebookservice.commons.path.BrandPath.BRAND_URL;
import static hu.unideb.inf.notebookservice.commons.path.BrandPath.BRANDS_URL;
import static hu.unideb.inf.notebookservice.commons.path.BrandPath.BRAND_ID_URL;

@RestController
@RequiredArgsConstructor
public class BrandRestController {

    private final BrandService brandService;

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = BRAND_URL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> responseEntity(@RequestBody BrandRequest brandRequest) {
        ResponseEntity result;
        try {
            brandService.saveBrand(brandRequest);
            result = ResponseEntity.ok(brandRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
        }
        return result;
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = BRAND_ID_URL)
    public ResponseEntity<?> getBrandByID(@PathVariable Long id) {
        Brand foundBrand = brandService.findById(id);
        return ResponseEntity.accepted().body(foundBrand);
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping(path = BRANDS_URL)
    public ResponseEntity<?> getAllBrand() {
        List<Brand> allBrand = brandService.findAll();
        return ResponseEntity.accepted().body(allBrand);
    }
}
