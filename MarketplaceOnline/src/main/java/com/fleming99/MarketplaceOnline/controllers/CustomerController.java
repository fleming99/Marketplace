package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final EntitiesServiceUseCase<Customer> customerEntitiesServiceUseCase;

    public CustomerController(EntitiesServiceUseCase<Customer> customerEntitiesServiceUseCase) {
        this.customerEntitiesServiceUseCase = customerEntitiesServiceUseCase;
    }

    @GetMapping("/customers-list")
    public String customerList(Model theModel){

        theModel.addAttribute("customers", customerEntitiesServiceUseCase.findAll());

        return "customers/customer-list";
    }

    @GetMapping("/create-customer")
    public String createCustomer(Model theModel){

        theModel.addAttribute("customer", new Customer());

        return "customers/create-customer";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        customerEntitiesServiceUseCase.save(customer);

        return "redirect:/customers/customers-list";
    }

    @GetMapping("/update-customer")
    public String updateCustomer(@RequestParam("customerId") int theId, Model theModel){

        theModel.addAttribute("customer", customerEntitiesServiceUseCase.findById(theId));

        return "customers/update-customer-form";
    }

    @GetMapping("/delete-customer")
    public String deleteCustomer(@RequestParam("customerId") int theId){

        customerEntitiesServiceUseCase.deleteById(theId);

        return "redirect:/customers/customers-list";
    }
}
