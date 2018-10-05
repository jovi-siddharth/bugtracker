package com.bub.sho.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoleForm {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;
	
	private String formRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFormRole() {
		return formRole;
	}

	public void setFormRole(String formRole) {
		this.formRole = formRole;
	}
}
