package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.CartItemRepository;
import com.fleming99.MarketplaceOnline.core.entities.CartItem;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements EntitiesServiceUseCase<CartItem> {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findById(int theId) {

        Optional<CartItem> kartItem = cartItemRepository.findById(theId);

        if (kartItem.isEmpty()){
            throw new RuntimeException("Did not found the kart item by id: " + theId);
        }

        return kartItem.get();
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteById(int theId) {
        cartItemRepository.deleteById(theId);
    }
}
