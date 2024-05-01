package com.fleming99.MarketplaceOnline.core.usecases;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.entities.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Customer findByEmail(String email);

    void save(WebUser webUser);
}
