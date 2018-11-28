package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.request.BrandRequest;
import hu.unideb.inf.notebookservice.persistence.entity.BrandEntity;
import hu.unideb.inf.notebookservice.persistence.repository.BrandRepository;
import hu.unideb.inf.notebookservice.service.converter.brand.BrandEntityListToBrandListConverter;
import hu.unideb.inf.notebookservice.service.converter.brand.BrandEntityToBrandConverter;
import hu.unideb.inf.notebookservice.service.converter.brand.BrandRequestToBrandConverter;
import hu.unideb.inf.notebookservice.service.converter.brand.BrandToBrandEntityConverter;
import hu.unideb.inf.notebookservice.service.domain.Brand;
import hu.unideb.inf.notebookservice.service.interfaces.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;
    private final BrandToBrandEntityConverter toEntity;
    private final BrandRequestToBrandConverter fromRequest;
    private final BrandEntityToBrandConverter toDomain;
    private final BrandEntityListToBrandListConverter toDomainList;

    @Override
    public void saveBrand(BrandRequest brandRequest) {

        log.info(">> Converting Request >> [brandRequest:{}]", brandRequest);
        Brand brand = fromRequest.convert(brandRequest);

        log.info(">> Converting Domain >> [brand:{}]", brand);
        BrandEntity converted = toEntity.convert(brand);

        assert converted != null;
        log.info(">> Saving Entity >> [converted:{}]", converted);
        repository.save(converted);
    }

    @Override
    public Brand findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        BrandEntity foundBrand = repository.getOne(id);

        log.info(">> Converting to Domain >> [foundBrand:{}]", foundBrand);
        Brand convertedBrand = toDomain.convert(foundBrand);

        log.info(">> Response >> [convertedBrand:{}]", convertedBrand);
        return convertedBrand;
    }

    @Override
    public List<Brand> findAll() {

        log.info(">> Finding all Brand <<");
        List<BrandEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain >> [entityList:{}]", entityList);
        List<Brand> brandList = toDomainList.convert(entityList);

        log.info(">> Response >> [brandList:{}]", brandList);
        return brandList;
    }
}
