package com.fleming99.MarketplaceOnline.core.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

    public Company() {
    }

    public Company(int companyId, String companyName, String tradingName, String companyEmail, String companyCnpj) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.tradingName = tradingName;
        this.companyEmail = companyEmail;
        this.companyCnpj = companyCnpj;
    }
}
