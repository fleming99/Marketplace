package com.fleming99.MarketplaceOnline.adapters;

import com.fleming99.MarketplaceOnline.core.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
