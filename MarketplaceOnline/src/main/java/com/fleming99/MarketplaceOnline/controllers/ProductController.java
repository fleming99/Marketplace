package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Category;
import com.fleming99.MarketplaceOnline.core.entities.Product;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final EntitiesServiceUseCase<Category> categoryEntitiesServiceUseCase;
    private final EntitiesServiceUseCase<Product> productEntitiesServiceUseCase;

    public ProductController(EntitiesServiceUseCase<Product> productEntitiesServiceUseCase, EntitiesServiceUseCase<Category> categoryEntitiesServiceUseCase) {
        this.productEntitiesServiceUseCase = productEntitiesServiceUseCase;
        this.categoryEntitiesServiceUseCase = categoryEntitiesServiceUseCase;
    }

    @GetMapping("/products-list")
    public String productsList(Model theModel){

        theModel.addAttribute("products", productEntitiesServiceUseCase.findAll());

        return "products/product-list";
    }

    @GetMapping("/create-product")
    public String createProduct(Model theModel){

        List<Category> listCategories = categoryEntitiesServiceUseCase.findAll();

        theModel.addAttribute("product", new Product());
        theModel.addAttribute("listCategories", listCategories);

        return "products/create-product";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") Product product){

        productEntitiesServiceUseCase.save(product);

        return "redirect:/products/products-list";
    }

    @GetMapping("/update-product")
    public String updateProduct(@RequestParam("productId") int theId, Model theModel){

        theModel.addAttribute("product", productEntitiesServiceUseCase.findById(theId));

        return "products/update-product-form";
    }

    @GetMapping("/delete-product")
    public String deleteProduct(@RequestParam("productId") int theId){

        productEntitiesServiceUseCase.deleteById(theId);

        return "redirect:/products/products-list";
    }
}
