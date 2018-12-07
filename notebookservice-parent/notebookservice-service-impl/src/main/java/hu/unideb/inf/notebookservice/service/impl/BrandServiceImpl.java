package hu.unideb.inf.notebookservice.service.impl;

import hu.unideb.inf.notebookservice.commons.exeptions.AlreadyExistsException;
import hu.unideb.inf.notebookservice.commons.exeptions.NotFoundException;
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
import java.util.Optional;

import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ALREADY_EXISTS_EXCEPTION;
import static hu.unideb.inf.notebookservice.commons.error.ErrorTemplate.ID_NOT_FOUND_EXCEPTION;

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
    public Brand save(BrandRequest brandRequest) {

        Optional<BrandEntity> entity = repository.findByName(brandRequest.getName());
        if (entity.isPresent()) {
            throw new AlreadyExistsException(String.format(ALREADY_EXISTS_EXCEPTION, brandRequest.getName()));
        }

        log.info(">> Converting Request >> [brandRequest:{}]", brandRequest.getName());
        Brand brand = fromRequest.convert(brandRequest);

        log.info(">> Converting Domain >> [brand:{}]", brand.getName());
        BrandEntity converted = toEntity.convert(brand);

        log.info(">> Saving Entity >> [converted:{}]", converted.getName());
        BrandEntity saved = repository.save(converted);

        log.info(">> Response >> [saved:{}]", saved.getName());
        return toDomain.convert(saved);
    }

    @Override
    public Brand update(Brand brand) {
        return null;
    }

    @Override
    public Brand findById(Long id) {

        log.info(">> Searching in Database >> [id:{}]", id);
        Optional<BrandEntity> foundBrand = repository.findById(id);

        log.info(">> Converting to Domain >> [foundBrand:{}]", foundBrand.get().getName());
        Brand convertedBrand = toDomain.convert(
                foundBrand.orElseThrow(
                        () -> new NotFoundException(
                                String.format(ID_NOT_FOUND_EXCEPTION, id))));

        log.info(">> Response >> [convertedBrand:{}]", convertedBrand.getName());
        return convertedBrand;
    }

    @Override
    public List<Brand> findAll() {

        log.info(">> Finding all Brand <<");
        List<BrandEntity> entityList = repository.findAll();

        log.info(">> Converting all to Domain <<");
        List<Brand> brandList = toDomainList.convert(entityList);

        log.info(">> Response <<");
        return brandList;
    }
}
