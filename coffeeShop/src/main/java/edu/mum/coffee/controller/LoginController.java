package edu.mum.coffee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.model.PersonDto;
import edu.mum.coffee.model.UserDto;
import edu.mum.coffee.service.PersonServiceClient;

@Controller
public class LoginController {
	
	@Autowired
	private PersonServiceClient personServiceClient;

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public String login(@ModelAttribute("user") UserDto user){
		return "login";
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(@ModelAttribute("person") PersonDto peron){
		return "registration";
	}	
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("person") PersonDto person, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();		
		
		PersonDto userExists = personServiceClient.findByEmail(person.getEmail());		
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			personServiceClient.createPerson(person);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.setViewName("registration");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PersonDto person = personServiceClient.findByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + person.getEmail());
		modelAndView.addObject("adminMessage","Welcome to admin home");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

}
