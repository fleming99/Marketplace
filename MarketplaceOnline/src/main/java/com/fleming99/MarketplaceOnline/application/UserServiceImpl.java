package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.adapters.CustomerRepository;
import com.fleming99.MarketplaceOnline.adapters.UserRoleRepository;
import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.entities.UserRole;
import com.fleming99.MarketplaceOnline.core.usecases.EntityDao;
import com.fleming99.MarketplaceOnline.core.usecases.RoleDao;
import com.fleming99.MarketplaceOnline.core.usecases.UserService;
import com.fleming99.MarketplaceOnline.dao.CustomerDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private final EntityDao<Customer> customerEntityDao;
    private final RoleDao roleDao;

    @Autowired
    public UserServiceImpl(EntityDao<Customer> customerEntityDao, RoleDao roleDao) {
        this.customerEntityDao = customerEntityDao;
        this.roleDao = roleDao;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerEntityDao.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerEntityDao.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

        Collection<SimpleGrantedAuthority>authorities =mapRolesToAuthorities(customer.getRoles());

        return new org.springframework.security.core.userdetails.User(customer.getCustomerEmail(), customer.getCustomerPassword(), authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (UserRole tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getRole());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}
