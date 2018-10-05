package com.bub.sho.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bub.sho.dao.RoleDAO;
import com.bub.sho.dao.TicketDao;
import com.bub.sho.dao.UserDAO;
import com.bub.sho.entity.Priority;
import com.bub.sho.entity.Role;
import com.bub.sho.entity.Status;
import com.bub.sho.entity.Ticket;
import com.bub.sho.entity.User;
import com.bub.sho.model.HistoryTracker;
import com.bub.sho.model.NewTicket;
import com.bub.sho.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDao;

	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser userForm) {
		
		User user = new User();
		 // assign user details to the user object
		user.setUserName(userForm.getUserName());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setEmail(userForm.getEmail());

		// give user default role of "employee"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));

		 // save user in the database
		userDao.save(user);
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		userDao.save(user);
		
	}

	@Override
	@Transactional
	public void saveTicket(NewTicket newTicket, String user, User assigned) {
		// TODO Auto-generated method stub
		Ticket ticket = new Ticket();
		ticket.setAssignedTo(assigned);
		ticket.setCreatedBy(userDao.findByUserName(user));
		ticket.setBug(newTicket.getBugStatement());
		ticket.setPriority(ticketDao.findPrioritybyValue(newTicket.getPriority()));
		ticket.setStatus(ticketDao.findStatusbyName("open"));
		ticketDao.save(ticket);
	}

	@Override
	@Transactional
	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		return ticketDao.getTickets();
	}

	@Override
	@Transactional
	public List<Ticket> getTickets(String keyWord) {
		// TODO Auto-generated method stub
		return ticketDao.getTickets(keyWord);
	}

	@Override
	@Transactional
	public Ticket getTicket(long theId) {
		// TODO Auto-generated method stub
		return ticketDao.getTicket(theId);
	}

	@Override
	@Transactional
	public void deleteTicket(long theId) {

		ticketDao.deleteTicket(theId);
	}

	@Override
	@Transactional
	public void updateTicket(@Valid Ticket ticket,String comment) {

		ticketDao.save(ticket,comment);
	}

	@Override
	@Transactional
	public Priority getPrioritybyVal(Integer val) {
		// TODO Auto-generated method stub
		return ticketDao.findPrioritybyValue(val);
	}

	@Override
	@Transactional
	public Status getStatusbyName(String name) {
		// TODO Auto-generated method stub
		return ticketDao.findStatusbyName(name);
	}

	@Override
	@Transactional
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		return roleDao.findAllRoles();
	}

	@Override
	@Transactional
	public List<Priority> findAllPriorities() {
		// TODO Auto-generated method stub
		return ticketDao.findAllPriorities();
	}

	@Override
	@Transactional
	public List<Status> findAllStatuses() {
		// TODO Auto-generated method stub
		return ticketDao.findAllStatuses();
	}

	@Override
	@Transactional
	public List<HistoryTracker> getTicketHistory(long theId) {
		return ticketDao.getTicketHistory(theId);
	}

	@Override
	@Transactional
	public Role getRolebyName(String name) {
		// TODO Auto-generated method stub
		return roleDao.findRoleByName(name);
	}

}
