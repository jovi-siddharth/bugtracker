package com.bub.sho.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.bub.sho.validator.ValidDate;

@Entity
@Table(name="ticket")
@Audited
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="created_by")
	private User createdBy;
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="assigned_to")
	private User assignedTo;
	
	//@DateTimeFormat
	//@Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}",message="input date in dd/mm/yyyy")
	@CreationTimestamp
	@Column(name="created_on",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@ValidDate
	private Date createdOn;
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="status",nullable=false)
	private Status status;
	
	@Column(name="bug")
	private String bug;
	
	//@DateTimeFormat
	@UpdateTimestamp
	@Column(name="last_modified",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotAudited
	private Date lastModified;
	
	@OneToOne(cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="priority")
	private Priority priority;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="ticketId",fetch=FetchType.LAZY)
	@NotAudited
	private List<Comments> comments;
	
	/*private String comment;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}*/

	public List<Comments> getComments() {
		return comments;
	}

	/*public void setComments(List<Comments> comments) {
		this.comments = comments;
	}*/

	public Ticket() {
		//createdOn = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
		//lastModified = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
	}

	public Ticket(User createdBy, User assignedTo, Date createdOn, Status status, String bug, Date lastModified,
			Priority priority) {
		super();
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		this.createdOn = createdOn;
		this.status = status;
		this.bug = bug;
		this.lastModified = lastModified;
		this.priority = priority;
	}
	
	/*@PrePersist
	@PreUpdate
	public void prePersist() {
		if(createdOn==null) {
			createdOn = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
		}
		if(lastModified==null) {
			lastModified = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
		}
		if(priority==null) {
			priority = new Priority((short)1);
		}
		if(status==null) {
			status = new Status("open");
		}
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getBug() {
		return bug;
	}

	public void setBug(String bug) {
		this.bug = bug;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public void addComment(Comments comment) {
		if(comments==null) {
			comments = new ArrayList<Comments>();
		}
		comments.add(comment);
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", createdBy=" + createdBy + ", assignedTo=" + assignedTo + ", createdOn="
				+ createdOn + ", status=" + status + ", bug=" + bug + ", lastModified=" + lastModified + ", priority="
				+ priority + "comments="+comments+"]";
	}
	
		
}
