package com.fleming99.MarketplaceOnline.core.usecases;

import java.util.List;

public interface EntitiesService<T> {

    List<T> findAll();

    T findById(int theId);

    T save(T t);

    void deleteById(int theId);
}
