package com.fleming99.MarketplaceOnline.dao;

import com.fleming99.MarketplaceOnline.core.entities.UserRole;
import com.fleming99.MarketplaceOnline.core.usecases.RoleDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserRole findRoleByName(String theRoleName) {

        TypedQuery<UserRole> theQuery = entityManager.createQuery("from UserRole where role=:roleName", UserRole.class);
        theQuery.setParameter("roleName", theRoleName);

        UserRole userRole = null;

        try {
            userRole = theQuery.getSingleResult();
        }catch (Exception ignored){

        }

        return userRole;
    }
}
