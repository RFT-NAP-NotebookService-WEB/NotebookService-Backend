package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.pojo.request.BrandRequest;
import hu.unideb.inf.notebookservice.persistence.entity.BrandEntity;
import hu.unideb.inf.notebookservice.persistence.repository.BrandRepository;
import hu.unideb.inf.notebookservice.service.converter.BrandEntityListToBrandListConverter;
import hu.unideb.inf.notebookservice.service.converter.BrandEntityToBrandConverter;
import hu.unideb.inf.notebookservice.service.converter.BrandRequestToBrandConverter;
import hu.unideb.inf.notebookservice.service.converter.BrandToBrandEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import hu.unideb.inf.notebookservice.service.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final BrandToBrandEntityConverter toEntity;
    private final BrandRequestToBrandConverter fromRequest;
    private final BrandEntityToBrandConverter toDomain;
    private final BrandEntityListToBrandListConverter toList;

    @Override
    public void addBrand(BrandRequest brandRequest) {
        log.info("Converting Request -> " + brandRequest.getName());
        Brand brand = fromRequest.convert(brandRequest);
        assert brand != null;
        log.info("Converting Domain -> " + brand.getName());
        BrandEntity converted = toEntity.convert(brand);
        assert converted != null;
        log.info("Saving Entity -> " + converted.getName());
        repository.save(converted);
    }

    @Override
    public Brand findByName(String name) {
        BrandEntity foundBrand = repository.findByName(name);
        return toDomain.convert(foundBrand);
    }

    @Override
    public List<Brand> findAll() {
        return toList.convert(repository.findAll());
    }
}
