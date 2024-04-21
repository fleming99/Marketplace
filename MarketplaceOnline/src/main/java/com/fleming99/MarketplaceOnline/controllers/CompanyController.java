package com.fleming99.MarketplaceOnline.controllers;

import com.fleming99.MarketplaceOnline.core.entities.Company;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/companys")
public class CompanyController {

    private final EntitiesServiceUseCase<Company> companyEntitiesServiceUseCase;

    public CompanyController(EntitiesServiceUseCase<Company> companyEntitiesServiceUseCase) {
        this.companyEntitiesServiceUseCase = companyEntitiesServiceUseCase;
    }

    @GetMapping("/companys-list")
    public String companyList(Model theModel){

        theModel.addAttribute("companys", companyEntitiesServiceUseCase.findAll());

        return "companys/company-list";
    }

    @GetMapping("/create-company")
    public String createCompany(Model theModel){

        theModel.addAttribute("company", new Company());

        return "companys/create-company";
    }

    @PostMapping("/save-company")
    public String saveCompany(@ModelAttribute("company") Company company){

        companyEntitiesServiceUseCase.save(company);

        return "redirect:/companys/companys-list";
    }

    @GetMapping("/update-company")
    public String updateCompany(@RequestParam("companyId") int theId, Model theModel){

        theModel.addAttribute("company", companyEntitiesServiceUseCase.findById(theId));

        return "companys/update-company-form";
    }

    @GetMapping("/delete-company")
    public String deleteCompany(@RequestParam("companyId") int theId){

        companyEntitiesServiceUseCase.deleteById(theId);

        return "redirect:/companys/companys-list";
    }
}
