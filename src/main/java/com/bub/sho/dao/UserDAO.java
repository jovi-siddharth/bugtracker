package com.bub.sho.dao;

import java.util.Collection;

import com.bub.sho.entity.Role;
import com.bub.sho.entity.User;

public interface UserDAO {

	User findByUserName(String userName);
    
    void save(User user);
    
    Collection<Role> getRolesByUser(User user);
}
