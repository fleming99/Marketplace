package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Company;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final EntitiesServiceUseCase<Company> companyEntitiesServiceUseCase;

    public CompanyController(EntitiesServiceUseCase<Company> companyEntitiesServiceUseCase) {
        this.companyEntitiesServiceUseCase = companyEntitiesServiceUseCase;
    }

    @GetMapping("/companies-list")
    public String companyList(Model theModel){

        theModel.addAttribute("companies", companyEntitiesServiceUseCase.findAll());

        return "companies/company-list";
    }

    @GetMapping("/create-company")
    public String createCompany(Model theModel){

        theModel.addAttribute("company", new Company());

        return "companies/create-company";
    }

    @PostMapping("/save-company")
    public String saveCompany(@ModelAttribute("company") Company company){

        companyEntitiesServiceUseCase.save(company);

        return "redirect:/companies/companies-list";
    }

    @GetMapping("/update-company")
    public String updateCompany(@RequestParam("companyId") int theId, Model theModel){

        theModel.addAttribute("company", companyEntitiesServiceUseCase.findById(theId));

        return "companies/update-company-form";
    }

    @GetMapping("/delete-company")
    public String deleteCompany(@RequestParam("companyId") int theId){

        companyEntitiesServiceUseCase.deleteById(theId);

        return "redirect:/companies/companies-list";
    }
}
