package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Company;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final EntitiesService<Company> companyEntitiesService;

    @Autowired
    public CompanyController(EntitiesService<Company> companyEntitiesService) {
        this.companyEntitiesService = companyEntitiesService;
    }

    @GetMapping("/companies-list")
    public String companyList(Model theModel){

        theModel.addAttribute("companies", companyEntitiesService.findAll());

        return "companies/company-list";
    }

    @GetMapping("/create-company")
    public String createCompany(Model theModel){

        theModel.addAttribute("company", new Company());

        return "companies/create-company";
    }

    @PostMapping("/save-company")
    public String saveCompany(Company company){

        companyEntitiesService.save(company);

        return "redirect:/companies/company-list";
    }

    @GetMapping("/update-company")
    public String updateCompany(@RequestParam("companyId") int theId, Model theModel){

        theModel.addAttribute("company", companyEntitiesService.findById(theId));

        return "companies/update-company-form";
    }

    @GetMapping("/delete-company")
    public String deleteCompany(@RequestParam("companyId") int theId){

        companyEntitiesService.deleteById(theId);

        return "redirect:/companies/companies-list";
    }
}
