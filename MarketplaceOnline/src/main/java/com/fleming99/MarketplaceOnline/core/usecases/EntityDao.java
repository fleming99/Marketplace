package com.fleming99.MarketplaceOnline.core.usecases;


public interface EntityDao<T> {

    T findByEmail(String email);
}
