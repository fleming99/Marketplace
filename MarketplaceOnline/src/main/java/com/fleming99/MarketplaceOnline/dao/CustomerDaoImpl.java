package com.fleming99.MarketplaceOnline.dao;


import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.usecases.EntityDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl implements EntityDao<Customer> {

    private final EntityManager entityManager;

    public CustomerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Customer findByEmail(String email) {

        TypedQuery<Customer> theQuery = entityManager.createQuery("from Customer where customerEmail=:email and activeProfile=true", Customer.class);
        theQuery.setParameter("email", email);

        Customer customer = null;

        try{
            customer = theQuery.getSingleResult();
        }catch (Exception ignored){
        }

        return customer;
    }
}
