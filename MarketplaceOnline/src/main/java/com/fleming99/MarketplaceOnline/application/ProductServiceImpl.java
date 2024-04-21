package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.ProductRepository;
import com.fleming99.MarketplaceOnline.core.entities.Product;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements EntitiesServiceUseCase<Product> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {

        Optional<Product> product = productRepository.findById(theId);

        if (product.isEmpty()){
            throw new RuntimeException("Did not found the product by id " + theId);
        }

        return product.get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }
}
