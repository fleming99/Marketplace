package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.CategoryRepository;
import com.fleming99.MarketplaceOnline.core.entities.Category;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements EntitiesServiceUseCase<Category> {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int theId) {

        Optional<Category> category = categoryRepository.findById(theId);

        if(category.isEmpty()){
            throw new RuntimeException("Did not found the category by id: " + theId);
        }
        return category.get();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }


}
