package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.UserRoleRepository;
import com.fleming99.MarketplaceOnline.core.entities.UserRole;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;

import java.util.List;
import java.util.Optional;

public class UserRoleServiceImpl implements EntitiesService<UserRole> {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(int theId) {

        Optional<UserRole> userRole = userRoleRepository.findById(theId);

        if(userRole.isEmpty()){
            throw new RuntimeException("Did not found the user role by id: " + theId);
        }

        return userRole.get();
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void deleteById(int theId) {
        userRoleRepository.deleteById(theId);
    }
}
