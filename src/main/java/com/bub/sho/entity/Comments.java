package com.bub.sho.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="comments")
//@Audited
public class Comments {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	@DateTimeFormat
	@CreationTimestamp
	@Column(name="created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="ticket_id")
	private Ticket ticketId;

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public Ticket getTicketId() {
		return ticketId;
	}

	public Comments(Date createdOn, String content, Ticket ticketId) {
		super();
		this.createdOn = createdOn;
		this.content = content;
		this.ticketId = ticketId;
	}

	@Override
	public String toString() {
		return "Comments [content=" + content + "]";
	}

	public Comments() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTicketId(Ticket ticketId) {
		this.ticketId = ticketId;
	}
		
	
	
}
