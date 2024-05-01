package com.fleming99.MarketplaceOnline.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_details")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_first_name")
    private String customerFirstName;

    @Column(name = "customer_last_name")
    private String customerLastName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_password")
    private String customerPassword;

    @Column(name = "customer_cpf")
    private String customerCpf;

    @Column(name = "active_profile")
    private boolean activeProfile;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<UserRole> roles;

    public Customer(String customerEmail, String customerPassword, Collection<UserRole> roles, boolean activeProfile){
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.activeProfile = activeProfile;
        this.roles = roles;
    }
}
