package com.bub.sho.dao;

import java.util.List;

import com.bub.sho.entity.Role;

public interface RoleDAO {

	public Role findRoleByName(String theRoleName);

	public List<Role> findAllRoles();
	
}
