package hu.unideb.inf.notebookservice.service.service;

import hu.unideb.inf.notebookservice.commons.pojo.request.BrandRequest;
import hu.unideb.inf.notebookservice.service.domain.Brand;

import java.util.List;

public interface BrandService {

    void addBrand(BrandRequest brandRequest);

    Brand findByName(String name);

    List<Brand> findAll();
}
