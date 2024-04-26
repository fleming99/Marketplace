package com.fleming99.MarketplaceOnline.core.usecases;

import com.fleming99.MarketplaceOnline.core.entities.UserRole;

public interface RoleDao {

    UserRole findRoleByName(String theRoleName);
}
