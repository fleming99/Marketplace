package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.CartDetails;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carts")
public class CartDetailsController {

    private final EntitiesServiceUseCase<CartDetails> cartDetailsEntitiesServiceUseCase;

    public CartDetailsController(EntitiesServiceUseCase<CartDetails> cartDetailsEntitiesServiceUseCase) {
        this.cartDetailsEntitiesServiceUseCase = cartDetailsEntitiesServiceUseCase;
    }

    @GetMapping("/carts-list")
    public String cartList(Model theModel){

        theModel.addAttribute("carts", cartDetailsEntitiesServiceUseCase.findAll());

        return "carts/cart-list";
    }

    @GetMapping("/create-cart")
    public String createCart(Model theModel){

        theModel.addAttribute("cart", new CartDetails());

        return "carts/create-cart";
    }

    @PostMapping("/save-cart")
    public String saveCart(@ModelAttribute("cart") CartDetails cartDetails){

        cartDetailsEntitiesServiceUseCase.save(cartDetails);

        return "redirect:/carts/carts-list";
    }

    @GetMapping("/update-cart")
    public String updateCart(@RequestParam("cartId") int theId, Model theModel){

        theModel.addAttribute("cart", cartDetailsEntitiesServiceUseCase.findById(theId));

        return "carts/update-cart-form";
    }

    @GetMapping("/delete-cart")
    public String deleteCart(@RequestParam("cartId") int theId){

        cartDetailsEntitiesServiceUseCase.deleteById(theId);

        return "redirect:/carts/carts-list";
    }
}
