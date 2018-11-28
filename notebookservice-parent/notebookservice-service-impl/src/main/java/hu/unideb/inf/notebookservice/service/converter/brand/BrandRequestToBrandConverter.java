package hu.unideb.inf.notebookservice.service.converter.brand;

import hu.unideb.inf.notebookservice.commons.request.BrandRequest;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BrandRequestToBrandConverter implements Converter<BrandRequest, Brand> {

    @Override
    public Brand convert(BrandRequest brandRequest) {
        return Brand.builder()
                .name(brandRequest.getName())
                .build();
    }
}
