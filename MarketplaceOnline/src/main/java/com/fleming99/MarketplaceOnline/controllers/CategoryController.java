package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Category;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ControllerAdvice
@RequestMapping("/categories")
public class CategoryController {

    private final EntitiesServiceUseCase<Category> categoryEntitiesServiceUseCase;

    public CategoryController(EntitiesServiceUseCase<Category> categoryEntitiesServiceUseCase) {
        this.categoryEntitiesServiceUseCase = categoryEntitiesServiceUseCase;
    }

    @ModelAttribute("categories")
    public List<Category> categoriesList(){

        return categoryEntitiesServiceUseCase.findAll();
    }

    @GetMapping("/categories-list")
    public String categoryList(Model theModel){

        theModel.addAttribute("categories", categoryEntitiesServiceUseCase.findAll());

        return "categories/category-list";
    }

    @GetMapping("/create-category")
    public String createCategory(Model theModel){

        theModel.addAttribute("category", new Category());

        return "categories/create-category";
    }

    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute("category") Category category){

        categoryEntitiesServiceUseCase.save(category);

        return "redirect:/categories/categories-list";
    }

    @GetMapping("/update-category")
    public String updateCategory(@RequestParam("categoryId") int theId, Model theModel){

        theModel.addAttribute("category", categoryEntitiesServiceUseCase.findById(theId));

        return "categories/update-category-form";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("categoryId") int theId){

        categoryEntitiesServiceUseCase.deleteById(theId);

        return "redirect:/categories/categories-list";
    }
}
