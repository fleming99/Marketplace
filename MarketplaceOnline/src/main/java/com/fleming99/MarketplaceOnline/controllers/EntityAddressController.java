package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.EntityAddress;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/addresses")
public class EntityAddressController {

    private final EntitiesService<EntityAddress> entityAddressEntitiesService;

    public EntityAddressController(EntitiesService<EntityAddress> entityAddressEntitiesService) {
        this.entityAddressEntitiesService = entityAddressEntitiesService;
    }

    @GetMapping("/addresses-list")
    public String addressesList(Model theModel){

        theModel.addAttribute("addresses", entityAddressEntitiesService.findAll());

        return "addresses/address-list";
    }

    @GetMapping("/create-address")
    public String createAddress(Model theModel){

        theModel.addAttribute("address", new EntityAddress());

        return "addresses/create-address";
    }

    @PostMapping("/save-address")
    public String saveAddress(@ModelAttribute("address") EntityAddress entityAddress){

        entityAddressEntitiesService.save(entityAddress);

        return "redirect:/addresses/addresses-list";
    }

    @GetMapping("/update-address")
    public String updateAddress(@RequestParam("addressId") int theId, Model theModel){

        theModel.addAttribute("address", entityAddressEntitiesService.findById(theId));

        return "addresses/update-address-form";
    }

    @GetMapping("/delete-address")
    public String deleteAddress(@RequestParam("addressId") int theId){

        entityAddressEntitiesService.deleteById(theId);

        return "redirect:/addresses/addresses-list";
    }
}
