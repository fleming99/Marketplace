package com.fleming99.MarketplaceOnline.core.usecases;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.validation.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService<T, U> extends UserDetailsService {

    T findByEmail(String email);

    void save(U webUser);
}
