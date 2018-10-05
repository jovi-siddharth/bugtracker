package com.bub.sho.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bub.sho.entity.Role;
import com.bub.sho.entity.Status;

@Repository
public class RoleDAOImpl implements RoleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String theRoleName) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using name
		Query<Role> theQuery = currentSession.createQuery("from Role where name=:roleName", Role.class);
		theQuery.setParameter("roleName", theRoleName);
		
		Role theRole = null;
		
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		
		return theRole;
	}

	@Override
	public List<Role> findAllRoles() {

		Session currentSession = sessionFactory.getCurrentSession();
		Query<Role> theQuery = currentSession.createQuery("from Role", Role.class);
		
		List<Role> role = null;
		
		try {
			role = theQuery.getResultList();
		} catch (Exception e) {
			role = null;
		}
		
		
		return role;
	}

}
