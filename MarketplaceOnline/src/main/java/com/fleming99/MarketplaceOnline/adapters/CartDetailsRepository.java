package com.fleming99.MarketplaceOnline.adapters;

import com.fleming99.MarketplaceOnline.core.entities.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Integer> {
}
