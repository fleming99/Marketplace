package com.fleming99.MarketplaceOnline.application;

import com.fleming99.MarketplaceOnline.core.entities.Customer;
import com.fleming99.MarketplaceOnline.core.entities.UserRole;
import com.fleming99.MarketplaceOnline.core.entities.WebUser;
import com.fleming99.MarketplaceOnline.core.usecases.EntitiesService;
import com.fleming99.MarketplaceOnline.core.usecases.EntityDao;
import com.fleming99.MarketplaceOnline.core.usecases.RoleDao;
import com.fleming99.MarketplaceOnline.core.usecases.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final EntitiesService<Customer> customerEntitiesService;
    private final EntityDao<Customer> customerEntityDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(EntitiesService<Customer> customerEntitiesService, EntityDao<Customer> customerEntityDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.customerEntitiesService = customerEntitiesService;
        this.customerEntityDao = customerEntityDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer findByEmail(String email) {
        return customerEntityDao.findByEmail(email);
    }

    @Override
    public void save(WebUser webUser) {
        Customer customer = new Customer();

        customer.setCustomerFirstName(webUser.getFirstName());
        customer.setCustomerLastName(webUser.getLastName());
        customer.setCustomerCpf(webUser.getCpf());
        customer.setCustomerEmail(webUser.getEmail());
        customer.setCustomerPassword(passwordEncoder.encode(webUser.getPassword()));
        customer.setActiveProfile(true);
        customer.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_GENERAL")));

        customerEntitiesService.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerEntityDao.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

        return new org.springframework.security.core.userdetails.User(customer.getCustomerEmail(), customer.getCustomerPassword(), mapRolesToAuthorities(customer.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
}
