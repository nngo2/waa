package edu.mum.coffee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@RestController
public class PersonRestController {
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value="/api/persons", method = RequestMethod.GET)
	public List<Person> getAllPersons() {
		return personService.findAll();
	}
	
	@RequestMapping(value="/api/persons/findbyemail", method = RequestMethod.GET)
	public Person getPersonByEmail(@RequestParam(value="email", required=true) String email) {
		return personService.findByEmail(email);
	}
	
	@RequestMapping(value="/api/persons", method = RequestMethod.POST)
	public Person createPerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
	
}
