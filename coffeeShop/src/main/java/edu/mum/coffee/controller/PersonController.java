package edu.mum.coffee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.model.PersonDto;
import edu.mum.coffee.service.PersonServiceClient;

@Controller
public class PersonController {
	@Autowired
	private PersonServiceClient personServiceClient;
	
	@RequestMapping(value="/admin/persons", method = RequestMethod.GET)
	public ModelAndView getAllProducts() {
		ModelAndView modelAndView = new ModelAndView();		
		List<PersonDto> persons = personServiceClient.getAllPersons();
		modelAndView.addObject("persons", persons);
		modelAndView.setViewName("admin/user");
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/persons/create", method = RequestMethod.GET)
	public String registration(@ModelAttribute("person") PersonDto person){
		return "admin/user_details";
	}	
	
	@RequestMapping(value = "/admin/persons/create", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid @ModelAttribute("person") PersonDto person, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();		
		
		PersonDto userExists = personServiceClient.findByEmail(person.getEmail());		
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/user_details");
		} else {
			personServiceClient.createPerson(person);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.setViewName("admin/user_details");
		}
		
		return modelAndView;
	}
}
