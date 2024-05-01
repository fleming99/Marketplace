package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.validation.WebUser;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import com.fleming99.MarketplaceOnline.core.usecases.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final EntitiesService<Customer> customerEntitiesService;

    private final UserService<Customer, WebUser> userService;

    private final Logger logger =Logger.getLogger(getClass().getName());

    @Autowired
    public CustomerController(EntitiesService<Customer> customerEntitiesService, UserService userService) {
        this.customerEntitiesService = customerEntitiesService;
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/customers-list")
    public String customerList(Model theModel){

        theModel.addAttribute("customers", customerEntitiesService.findAll());

        return "customers/customer-list";
    }

    @GetMapping("/create-customer")
    public String createCustomer(Model theModel){

        theModel.addAttribute("webUser", new WebUser());

        return "customers/create-customer";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(
           @Valid @ModelAttribute("webUser") WebUser webUser,
           BindingResult theBindingResult,
           HttpSession session, Model theModel){

        String email = webUser.getEmail();
        logger.info("Processing registration form for: " + webUser.getFirstName() + " " + webUser.getLastName());

        System.out.println("Debug: " + theBindingResult);

        // form validation
        if (theBindingResult.hasErrors()){
            System.out.println(theBindingResult.getAllErrors());
            System.out.println("teve erros");
            return "customers/create-customer";
        }

        // check the database if user already exists
        Customer existing = userService.findByEmail(email);
        if (existing != null){
            theModel.addAttribute("webUser", new WebUser());
            theModel.addAttribute("registrationError", "User already exists.");
            logger.warning("User already exists.");
            return "customers/create-customer";
        }else {
            // create user account and store in the database
            System.out.println("Não existe");
            userService.save(webUser);

            // place user in the web http session for later use
            session.setAttribute("user", webUser);

            return "redirect:/home";
        }
    }

    @GetMapping("/update-customer")
    public String updateCustomer(@RequestParam("customerId") int theId, Model theModel){

        theModel.addAttribute("customer", customerEntitiesService.findById(theId));

        return "customers/update-customer-form";
    }

    @GetMapping("/delete-customer")
    public String deleteCustomer(@RequestParam("customerId") int theId){

        customerEntitiesService.deleteById(theId);

        return "redirect:/customers/customers-list";
    }
}
