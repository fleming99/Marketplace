package com.fleming99.MarketplaceOnline.security;

import com.fleming99.MarketplaceOnline.application.UserServiceImpl;
import com.fleming99.MarketplaceOnline.core.usecases.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationSuccessHandler customAuthenticationSuccessHandler) throws Exception{

        httpSecurity.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/", "/index", "/login-directory/**", "/customers/create-customer", "/customers/save-customer", "/companies/create-company", "/categories/categories-list", "/static/**", "/css/**", "/images/**", "/index", "/home", "/sign-up", "products/products-list").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .defaultSuccessUrl("/home")
            )
            .logout(logout -> logout
                .permitAll().logoutSuccessUrl("/home")
            )
            .exceptionHandling(configurer -> configurer
                .accessDeniedPage("/access-denied")
            );

        return httpSecurity.build();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider authenticationProvider (UserService userService){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
