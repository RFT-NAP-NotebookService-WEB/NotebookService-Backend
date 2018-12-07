package hu.unideb.inf.notebookservice.service.interfaces;

import java.util.List;

public interface BaseService<S, T> {

    S save(T t);
    S update(S s);
    S findById(Long id);
    List<S> findAll();
}
