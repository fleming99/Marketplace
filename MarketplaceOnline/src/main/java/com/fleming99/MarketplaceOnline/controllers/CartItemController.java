package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.CartItem;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart-items")
public class CartItemController {

    private final EntitiesService<CartItem> cartItemEntitiesService;

    public CartItemController(EntitiesService<CartItem> cartItemEntitiesService) {
        this.cartItemEntitiesService = cartItemEntitiesService;
    }

    @GetMapping("/cart-items-list")
    public String cartItemsList(Model theModel){

        theModel.addAttribute("cartItems", cartItemEntitiesService.findAll());

        return "cart-items/cart-item-list";
    }

    @GetMapping("/create-cart-item")
    public String createCartItem(Model theModel){

        theModel.addAttribute("cart-item", new CartItem());

        return "cart-items/create-cart-item";
    }

    @PostMapping("/save-cart-item")
    public String saveCartItem(@ModelAttribute("cart-item") CartItem cartItem){

        cartItemEntitiesService.save(cartItem);

        return "redirect:/cart-items/cart-items-list";
    }

    @GetMapping("/update-cart-item")
    public String updateCartItem(@RequestParam("cartItemId") int theId, Model theModel){

        theModel.addAttribute("cart-item", cartItemEntitiesService.findById(theId));

        return "cart-items/update-cart-item-form";
    }

    @GetMapping("/delete-cart-item")
    public String deleteCartItem(@RequestParam("cartItemId") int theId){

        cartItemEntitiesService.deleteById(theId);

        return "redirect:/cart-items/cart-items-list";
    }
}
