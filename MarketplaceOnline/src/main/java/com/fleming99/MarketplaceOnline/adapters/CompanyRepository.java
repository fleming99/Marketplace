package com.fleming99.MarketplaceOnline.adapters;

import com.fleming99.MarketplaceOnline.core.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
