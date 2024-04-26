package com.fleming99.MarketplaceOnline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/home")
    public String showIndex(){
        return "landing-page/home";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login-directory/login-page";
    }

    @GetMapping("/sign-up")
    public String showSignUpPage(){
        return "login-directory/sign-up-page";
    }

}
