package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.CartDetailsRepository;
import com.fleming99.MarketplaceOnline.core.entities.CartDetails;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailsServiceImpl implements EntitiesServiceUseCase<CartDetails> {

    private final CartDetailsRepository cartDetailsRepository;

    @Autowired
    public CartDetailsServiceImpl(CartDetailsRepository cartDetailsRepository) {
        this.cartDetailsRepository = cartDetailsRepository;
    }

    @Override
    public List<CartDetails> findAll() {
        return cartDetailsRepository.findAll();
    }

    @Override
    public CartDetails findById(int theId) {

        Optional<CartDetails> kartDetails = cartDetailsRepository.findById(theId);

        if(kartDetails.isEmpty()){
            throw new RuntimeException("Did not found the kart by id: " + theId);
        }

        return kartDetails.get();
    }

    @Override
    public CartDetails save(CartDetails cartDetails) {
        return cartDetailsRepository.save(cartDetails);
    }

    @Override
    public void deleteById(int theId) {
        cartDetailsRepository.deleteById(theId);
    }
}
