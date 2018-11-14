package hu.unideb.inf.notebookservice.backend.rest;

import hu.unideb.inf.notebookservice.commons.pojo.request.BrandRequest;
import hu.unideb.inf.notebookservice.service.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BrandRestController {

    private final BrandService brandService;

    @RequestMapping(value = "/brand/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity responseEntity(BrandRequest brandRequest) throws Exception {
        ResponseEntity result;
        try {
            brandService.addBrand(brandRequest);
            result = ResponseEntity.ok(brandRequest);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("400");
        }
        return result;
    }
}
