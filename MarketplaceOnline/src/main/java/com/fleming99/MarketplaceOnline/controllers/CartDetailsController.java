package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.CartDetails;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carts")
public class CartDetailsController {

    private final EntitiesService<CartDetails> cartDetailsEntitiesService;

    public CartDetailsController(EntitiesService<CartDetails> cartDetailsEntitiesService) {
        this.cartDetailsEntitiesService = cartDetailsEntitiesService;
    }

    @GetMapping("/carts-list")
    public String cartList(Model theModel){

        theModel.addAttribute("carts", cartDetailsEntitiesService.findAll());

        return "carts/cart-list";
    }

    @GetMapping("/create-cart")
    public String createCart(Model theModel){

        theModel.addAttribute("cart", new CartDetails());

        return "carts/create-cart";
    }

    @PostMapping("/save-cart")
    public String saveCart(@ModelAttribute("cart") CartDetails cartDetails){

        cartDetailsEntitiesService.save(cartDetails);

        return "redirect:/carts/carts-list";
    }

    @GetMapping("/update-cart")
    public String updateCart(@RequestParam("cartId") int theId, Model theModel){

        theModel.addAttribute("cart", cartDetailsEntitiesService.findById(theId));

        return "carts/update-cart-form";
    }

    @GetMapping("/delete-cart")
    public String deleteCart(@RequestParam("cartId") int theId){

        cartDetailsEntitiesService.deleteById(theId);

        return "redirect:/carts/carts-list";
    }
}
