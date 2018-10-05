package com.bub.sho.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewTicket {
	
	@NotNull(message="is required")
	private String toUser;
	
	@NotNull(message="is required")
	private String bugStatement;
	
	@NotNull(message="is required")
	@Min(value=1,message="should be >= 1")
	@Max(value=3,message="should be <= 3")
	private Integer priority;

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getBugStatement() {
		return bugStatement;
	}

	public void setBugStatement(String bugStatement) {
		this.bugStatement = bugStatement;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	

}
