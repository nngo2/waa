package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.coffee.model.PersonDto;
import edu.mum.coffee.service.PersonServiceClient;

@Controller
public class PersonController {
	@Autowired
	private PersonServiceClient personServiceClient;
	
	@RequestMapping(value="/admin/persons", method = RequestMethod.GET)
	public List<PersonDto> getAllPersons() {
		return personServiceClient.getAllPersons();
	}

}
