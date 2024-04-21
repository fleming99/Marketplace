package com.fleming99.MarketplaceOnline.adapters;

import com.fleming99.MarketplaceOnline.core.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
