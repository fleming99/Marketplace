package com.fleming99.MarketplaceOnline.security;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.usecases.UserService;
import com.fleming99.MarketplaceOnline.core.validation.WebUser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

   private final UserService<Customer, WebUser> userService;


    public CustomAuthenticationSuccessHandler(UserService<Customer, WebUser> userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var user = userService.findByEmail(authentication.getName());

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
