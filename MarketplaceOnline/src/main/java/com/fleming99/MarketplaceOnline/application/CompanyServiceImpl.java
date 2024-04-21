package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.CompanyRepository;
import com.fleming99.MarketplaceOnline.core.entities.Company;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesServiceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements EntitiesServiceUseCase<Company> {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(int theId) {

        Optional<Company> company = companyRepository.findById(theId);

        if (company.isEmpty()){
            throw new RuntimeException("Did not found the company by id: " + theId);
        }

        return company.get();
    }

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void deleteById(int theId) {
        companyRepository.deleteById(theId);
    }
}
