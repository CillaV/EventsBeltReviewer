package com.pv.eventsbeltreviewer.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pv.eventsbeltreviewer.models.User;
import com.pv.eventsbeltreviewer.services.UserService;
import com.pv.eventsbeltreviewer.validators.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserValidator userValidator;
	
	
// Main Page to login/register	
	@GetMapping("/")
	public String index(@ModelAttribute("user") User user) {
		return "loginRegPage.jsp";
	}

	
// Register New User	
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "loginRegPage.jsp";
		}
		else {
			User curUser = this.userService.registerUser(user);
			// to save to session -- get id
			session.setAttribute("user", curUser.getId());
			return "redirect:/events";	
		}
	}

	
// Login User
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, RedirectAttributes redirect) {
		// method type and variable which contains method to authenticate user
		boolean isAuthenticated = this.userService.authenticateUser(email, password);
		// if true
		if(isAuthenticated) {
			User validUser = this.userService.findByEmail(email);
			// when saving to session get id
			session.setAttribute("user", validUser.getId());
			return "redirect:/events";
		}else {
			redirect.addFlashAttribute("error", "Invalid Login. Try Again.");
			return "redirect:/";
		}		
	}

	
// Logout User
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	
}
