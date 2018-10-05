package com.bub.sho.model;

import org.hibernate.envers.DefaultRevisionEntity;

import com.bub.sho.entity.Ticket;
import com.bub.sho.entity.UserRevisionEntity;

public class HistoryTracker{
	
	private Ticket ticket;
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public UserRevisionEntity getEnt() {
		return ent;
	}
	public void setEnt(UserRevisionEntity ent) {
		this.ent = ent;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	private UserRevisionEntity ent;
	private String changeType;
	public HistoryTracker(Ticket ticket, UserRevisionEntity ent, String changeType) {
		super();
		this.ticket = ticket;
		this.ent = ent;
		this.changeType = changeType;
	}
	public HistoryTracker() {
		
	}
	

}
