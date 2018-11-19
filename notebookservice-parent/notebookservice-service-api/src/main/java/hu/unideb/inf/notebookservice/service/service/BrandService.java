package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.BrandRequest;
import hu.unideb.inf.notebookservice.service.domain.Brand;

import java.util.List;

public interface BrandService {

    void saveBrand(BrandRequest brandRequest);

    Brand findById(Long id);

    List<Brand> findAll();
}
