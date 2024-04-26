package com.fleming99.MarketplaceOnline.adapters;

import com.fleming99.MarketplaceOnline.core.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
}
