package com.bub.sho.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bub.sho.entity.Priority;
import com.bub.sho.entity.Role;
import com.bub.sho.entity.Status;
import com.bub.sho.entity.Ticket;
import com.bub.sho.entity.User;
import com.bub.sho.model.HistoryTracker;
import com.bub.sho.model.NewTicket;
import com.bub.sho.user.CrmUser;

public interface UserService extends UserDetailsService{

	User findByUserName(String userName);

    void save(CrmUser userForm);
    
    void updateUser(User user);
    
    void saveTicket(NewTicket newTicket, String user,User assigned);

	List<Ticket> getTickets();

	List<Ticket> getTickets(String keyWord);

	Ticket getTicket(long theId);

	void deleteTicket(long theId);

	void updateTicket(@Valid Ticket ticket, String comment);
	
	Priority getPrioritybyVal(Integer val);
	
	Status getStatusbyName(String name);

	List<Role> findAllRoles();

	List<Priority> findAllPriorities();

	List<Status> findAllStatuses();

	List<HistoryTracker> getTicketHistory(long theId);

	Role getRolebyName(String name);
}
