package com.pv.eventsbeltreviewer.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pv.eventsbeltreviewer.models.Event;
import com.pv.eventsbeltreviewer.models.User;
import com.pv.eventsbeltreviewer.services.EventService;
import com.pv.eventsbeltreviewer.services.MessageService;
import com.pv.eventsbeltreviewer.services.UserService;

@Controller
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
// get current date/time --	
	private Date getDate() {
		Date now = new Date();
		return now;
	}
	
// verify user in session ??
//	public Long userSessionId(HttpSession session) {
//		if(session.getAttribute("userId") == null)
//			return null;
//		return (Long)session.getAttribute("userId");
//	}	
//	
	
	
// main events page after successful login/registration --
		@GetMapping("/events")
		public String welcomePage(HttpSession session, Model model, @ModelAttribute("event") Event event) {
			if (session.getAttribute("user") == null) {
				return "redirect:/";
			}
				else {	
				
				// get user from session (which is an id)
				Long id = (Long) session.getAttribute("user");
				// find user with id to get User instance
				User curUser = this.userService.findUserById(id);
				// save them in the model
				model.addAttribute("user", curUser);
				model.addAttribute("inState", this.eventService.eventsInState(curUser.getState()));
				model.addAttribute("outState", this.eventService.eventsNotInState(curUser.getState()));
				model.addAttribute("now", getDate());
				// return main page
				return "eventsIndex.jsp";
				}
		}
		

// Create a New Event		
		@PostMapping("/events/new")
		public String newEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model, HttpSession session) {
			if(result.hasErrors()) {
				Long id = (Long) session.getAttribute("user");
				// find user with id to get User instance
				User curUser = this.userService.findUserById(id);
				// save them in the model
				model.addAttribute("user", curUser);
				model.addAttribute("inState", this.eventService.eventsInState(curUser.getState()));
				model.addAttribute("outState", this.eventService.eventsNotInState(curUser.getState()));
				return "eventsIndex.jsp";
			}
			else {
				this.eventService.createEvent(event);
				return "redirect:/events";
			}
		}
	
	
// Show One Event
		@GetMapping("/events/{id}")
		public String showEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
			if (session.getAttribute("user") == null) {
				return "redirect:/";
			}
			else {
			Long userId = (Long) session.getAttribute("user");
			User curUser = this.userService.findUserById(userId);
			model.addAttribute("user", curUser);
			model.addAttribute("event", this.eventService.showEvent(id));
			return "showOneEvent.jsp";
			}
		}
	
// Edit Event
		@GetMapping("/events/{id}/edit")
		public String editEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
			if (session.getAttribute("user") == null) {
				return "redirect:/";
			}
			else {
			Long userId = (Long) session.getAttribute("user");
			User curUser = this.userService.findUserById(userId);
			model.addAttribute("user", curUser);
//			model.addAttribute("user", session.getAttribute(user));
			model.addAttribute("event", this.eventService.showEvent(id));
			model.addAttribute("now", getDate());
			return "editEvent.jsp";
			}
		}
		
		
// Update Event --
		@PutMapping("/events/{id}/update")
		public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, @PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirect) {
			if(result.hasErrors()) {
				Long userId = (Long) session.getAttribute("user");
				User curUser = this.userService.findUserById(userId);
				model.addAttribute("user", curUser);
				model.addAttribute("event", this.eventService.showEvent(id));
				redirect.addFlashAttribute("error", "Must not be blank");
				return "redirect:/events/{id}/edit";
			}
			else {
				this.eventService.updateEvent(event);
				return "redirect:/events/{id}";
			}
		}
		
		
// Delete Event --
		@DeleteMapping("events/{id}/delete")
		public String deleteEvent(@PathVariable("id") Long id) {
			this.eventService.deleteEvent(id);
			return "redirect:/events";
		}

		
// Joining an Event
		@GetMapping("/events/{eventId}/join/{guestId}")
		public String joinEvent(@PathVariable("eventId") Long eventId, @PathVariable("guestId") Long guestId, HttpSession session) {
			if (session.getAttribute("user") == null) {
				return "redirect:/";
			}
			this.eventService.joinEvent(eventId, guestId);
			return "redirect:/events";
		}
		
				
		
// Cancel Join an Event
		@GetMapping("/events/{eventId}/cancel/{guestId}")
		public String cancelJoin(@PathVariable("eventId") Long eventId, @PathVariable("guestId") Long guestId, HttpSession session) {
			if (session.getAttribute("user") == null) {
				return "redirect:/";
			}
			this.eventService.unJoinEvent(eventId, guestId);
			return "redirect:/events";
		}

		
// Add Comment
		@PostMapping("/events/{eventId}/addcomment/{userId}")
		public String addComment(@PathVariable("eventId") Long eventId, @PathVariable("userId") Long userId, @RequestParam("comments") String comment ,  HttpSession session, RedirectAttributes redirect ) {
			if (session.getAttribute("user") == null) {
				return "redirect:/";
			}
			if(comment.equals("")) {
				redirect.addFlashAttribute("error", "Comment must not be blank");
				return "redirect:/events/{eventId}";
			}
			else {
				this.messageService.saveComment(eventId, userId, comment);
				return "redirect:/events/{eventId}";
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
}
