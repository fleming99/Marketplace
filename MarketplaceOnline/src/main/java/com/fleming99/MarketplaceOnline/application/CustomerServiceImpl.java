package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.CustomerRepository;
import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements EntitiesService<Customer> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int theId) {

        Optional<Customer> customer = customerRepository.findById(theId);

        if (customer.isEmpty()){
            throw new RuntimeException("Did not found the customer by id: " + theId);
        }
        return customer.get();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }
}
