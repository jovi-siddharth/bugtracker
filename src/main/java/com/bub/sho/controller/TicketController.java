package com.bub.sho.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bub.sho.config.IAuthenticationFacade;
import com.bub.sho.entity.Priority;
import com.bub.sho.entity.Status;
import com.bub.sho.entity.Ticket;
import com.bub.sho.entity.User;
import com.bub.sho.model.BugSearchForm;
import com.bub.sho.model.HistoryTracker;
import com.bub.sho.model.NewTicket;
import com.bub.sho.service.UserService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	private Logger logger = Logger.getLogger(getClass().getName());

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping("/")
	public String getTickets(Model tModel) {
		
		List<Ticket> tickets = userService.getTickets();
		tModel.addAttribute("bugSearchForm", new BugSearchForm());
		tModel.addAttribute("tickets",tickets);
		return "recent-tickets";
	}

	@GetMapping("/newTicketForm")
	public String newTicketForm(Model theModel) {
		
		theModel.addAttribute("newTicket", new NewTicket());
		
		
		return "new-ticket-form";
	}
	
	@PostMapping("/generateTicket")
	public String generateTicket(
			@Valid @ModelAttribute("newTicket") NewTicket newTicket, 
			BindingResult theBindingResult, 
			Model theModel) {
		if (theBindingResult.hasErrors()){
			 return "new-ticket-form";
	        }
		String assigned = newTicket.getToUser();
		User assignedUser = userService.findByUserName(assigned);
		logger.info("what the error priority "+ newTicket.getPriority());
		if(assignedUser==null) {
			theModel.addAttribute("newTicket", new NewTicket());
			theModel.addAttribute("registrationError", "User name Does not exists.");
			 return "new-ticket-form";
		}
		Authentication authentication = authenticationFacade.getAuthentication();
		String user = authentication.getName();
		userService.saveTicket(newTicket, user, assignedUser);
		
		return "redirect:/ticket/";
	}
	
	@PostMapping("/searchTicket")
	public String bugSearch(@Valid @ModelAttribute("bugSearchForm") BugSearchForm bugSearchForm, 
			BindingResult theBindingResult, 
			Model theModel) {
		if (theBindingResult.hasErrors()){
			 return "recent-tickets";
	        }
		List<Ticket> tickets = userService.getTickets(bugSearchForm.getKeyWord());
		theModel.addAttribute("bugSearchForm", new BugSearchForm());
		theModel.addAttribute("tickets",tickets);
		
		
		return "recent-tickets";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("ticketId") int theId, Model theModel) {
		Ticket ticket = userService.getTicket(theId);
		theModel.addAttribute("ticket",ticket);
		return "ticket-update-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("ticketId") long theId) {
		userService.deleteTicket(theId);
		return "redirect:/ticket/";
	}
	
	@GetMapping("/view")
	public String viewTicket(@RequestParam("ticketId") int theId, Model theModel) {
		Ticket ticket = userService.getTicket(theId);
		theModel.addAttribute("ticket",ticket);
		return "ticket-view";
	}
	
	@PostMapping("/updateTicket")
	public String updateTicket(
			@Valid @ModelAttribute("ticket") Ticket ticket,  BindingResult theBindingResult,
			Model theModel, @RequestParam("comment") String comment) {
		if (theBindingResult.hasErrors()){
			 return "ticket-update-form";
	        }
		logger.info("what the errorcomment"+comment);
		logger.info("what the error"+ticket);
		userService.updateTicket(ticket,comment);
		
		return "redirect:/ticket/view?ticketId="+ticket.getId();
	}
	
	@GetMapping("/history")
	public String historyTicket(@RequestParam("ticketId") long theId, Model theModel) {
		List<HistoryTracker> hisList= userService.getTicketHistory(theId);
		/*List<HistoryTracker> hisList = new ArrayList<HistoryTracker>();
		try {
			for(Object[] obj:historyList) {
				hisList.add(new HistoryTracker((Ticket)obj[0],(UserRevisionEntity)obj[1],(String)((RevisionType)obj[2]).toString()));
			}
		}catch(Exception e) {
			logger.info("what the errorc ticket controller"+e.getMessage());
		}*/
		theModel.addAttribute("hisList",hisList);
		//theModel.addAttribute("ticket",ticket);
		return "ticket-history-view";
	}
	
	
	@ModelAttribute("priorities")
    public List<Priority> initializePriorities() {
        return userService.findAllPriorities();
    }
	
	@ModelAttribute("statuses")
    public List<Status> initializeStatuses() {
        return userService.findAllStatuses();
    }
	
}
