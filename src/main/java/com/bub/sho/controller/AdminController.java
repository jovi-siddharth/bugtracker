package com.bub.sho.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bub.sho.entity.Role;
import com.bub.sho.entity.User;
import com.bub.sho.service.UserService;
import com.bub.sho.user.RoleForm;

@Controller
@RequestMapping("/systems")
public class AdminController {
	
	 @Autowired
	    private UserService userService;
	
	private Map<String, String> roles;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@PostConstruct
	protected void loadRoles() {
		
		// using hashmap, could also read this info from a database
		
		roles = new LinkedHashMap<String, String>();
		
		// key=the role, value=display to user 
		roles.put("ROLE_EMPLOYEE", "Employee");
		roles.put("ROLE_MANAGER", "Manager");
		roles.put("ROLE_ADMIN", "Admin");		
	}

	@GetMapping("/")
	public String showSystems() {
		
		return "systems";
	}
	
	@GetMapping("/updateUserRole")
	public String updateRolesForm(Model theModel){
		
		theModel.addAttribute("roleForm", new RoleForm());
		theModel.addAttribute("roles", roles);
		
		return "role-form";
	}
	
	@PostMapping("/processUserRoleForm")
	public String processUserRoleForm(
			@Valid @ModelAttribute("roleForm") RoleForm theRoleForm, 
			BindingResult theBindingResult, 
			Model theModel) {
		
		String user = theRoleForm.getUserName();
		
		if (theBindingResult.hasErrors()){
			 return "registration-form";
	        }
		
		User existing = userService.findByUserName(user);
		if(existing==null) {
			theModel.addAttribute("roleForm", new RoleForm());
			theModel.addAttribute("roles", roles);
			theModel.addAttribute("registrationError", "User name Does not exists.");
			return "role-form";
		}
		
		Collection<Role> existingRoles = existing.getRoles();
		String role = theRoleForm.getFormRole();
		for(Role r : existingRoles) {
			if(r.getName().equals(role)){
				
				theModel.addAttribute("roleForm", new RoleForm());
				theModel.addAttribute("roles", roles);
				theModel.addAttribute("registrationError", "Role already exists.");
				return "role-form";
			}
		}
		existingRoles.add(userService.getRolebyName(role));
		existing.setRoles(existingRoles);
		
		userService.updateUser(existing);
		
		return "user-role-confirmation";
		
		
	}
	
}
