package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.EntityAddressRepository;
import com.fleming99.MarketplaceOnline.core.entities.EntityAddress;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityAddressServiceImpl implements EntitiesService<EntityAddress> {

    private final EntityAddressRepository entityAddressRepository;

    @Autowired
    public EntityAddressServiceImpl(EntityAddressRepository entityAddressRepository) {
        this.entityAddressRepository = entityAddressRepository;
    }

    @Override
    public List<EntityAddress> findAll() {
        return entityAddressRepository.findAll();
    }

    @Override
    public EntityAddress findById(int theId) {

        Optional<EntityAddress> entityAddress = entityAddressRepository.findById(theId);

        if(entityAddress.isEmpty()){
            throw new RuntimeException("Did not found the address by id: " + theId);
        }

        return entityAddress.get();
    }

    @Override
    public EntityAddress save(EntityAddress entityAddress) {
        return entityAddressRepository.save(entityAddress);
    }

    @Override
    public void deleteById(int theId) {
        entityAddressRepository.deleteById(theId);
    }
}
