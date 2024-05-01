package com.fleming99.MarketplaceOnline.core.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_details")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private int companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "trading_name")
    private String tradingName;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "company_cnpj")
    private String companyCnpj;

    @Column(name = "company_password")
    private String companyPassword;

    @Column(name = "active_profile")
    private boolean activeProfile;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "company_role",
            joinColumns = @JoinColumn(name = "company_details_company_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<UserRole> roles;
}
