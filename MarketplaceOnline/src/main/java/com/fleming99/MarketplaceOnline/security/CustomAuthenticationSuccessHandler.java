package com.fleming99.MarketplaceOnline.security;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.usecases.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

   private final UserService userService;

    public CustomAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Customer customer = userService.findByEmail(authentication.getName());

        HttpSession session =request.getSession();
        session.setAttribute("user", customer);

        response.sendRedirect(request.getContextPath() + "/");
    }
}
