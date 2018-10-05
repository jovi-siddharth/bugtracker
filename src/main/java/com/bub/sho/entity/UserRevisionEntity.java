package com.bub.sho.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.bub.sho.listener.UserRevisionListener;

@Entity
//@Table(name="REVINFO")
@RevisionEntity(UserRevisionListener.class)
public class UserRevisionEntity {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @RevisionNumber
	 private int id;

	 public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@RevisionTimestamp
	 private long timestamp;
	private String username;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
}
