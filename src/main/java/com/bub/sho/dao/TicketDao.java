package com.bub.sho.dao;

import java.util.List;

import javax.validation.Valid;

import com.bub.sho.entity.Priority;
import com.bub.sho.entity.Status;
import com.bub.sho.entity.Ticket;
import com.bub.sho.model.HistoryTracker;

public interface TicketDao {
	
	public void save(Ticket ticket);
	
	public Priority findPrioritybyValue(Integer val);

	public Status findStatusbyName(String name);

	public List<Ticket> getTickets();

	public List<Ticket> getTickets(String keyWord);

	public Ticket getTicket(long theId);

	public void deleteTicket(long theId);

	public List<Priority> findAllPriorities();

	public List<Status> findAllStatuses();

	public void save(@Valid Ticket ticket, String comment);

	public List<HistoryTracker> getTicketHistory(long theId);

}
