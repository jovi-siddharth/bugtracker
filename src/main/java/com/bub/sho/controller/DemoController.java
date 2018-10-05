package com.bub.sho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bub.sho.entity.Role;
import com.bub.sho.service.UserService;

@Controller
public class DemoController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@ModelAttribute("roles")
    public List<Role> initializeRoles() {
        return userService.findAllRoles();
    }

}
