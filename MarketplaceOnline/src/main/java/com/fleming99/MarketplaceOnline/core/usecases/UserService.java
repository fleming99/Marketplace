package com.fleming99.MarketplaceOnline.core.usecases;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Customer findByEmail(String email);
}
