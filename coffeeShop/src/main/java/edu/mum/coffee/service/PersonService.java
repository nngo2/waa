package edu.mum.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.PersonRepository;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private UserService userService;

	public Person savePerson(Person person) {
		User user = new User(person.getEmail(), person.getPassword());
		userService.saveUser(user);	
		return personRepository.save(person);
	}
	
	public Person updatePerson(Person person) {
		Person cPerson = personRepository.findByEmail(person.getEmail());
		
		Address addr = person.getAddress();
		if (addr != null) {
			Address eAddr = cPerson.getAddress();
			if (eAddr != null) {
				addr.setId(eAddr.getId());
			}
			cPerson.setAddress(addr);
		}

		cPerson.setFirstName(person.getFirstName());
		cPerson.setLastName(person.getLastName());
		cPerson.setPhone(person.getPhone());
		
		return personRepository.save(cPerson);
	}

	public Person findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(Long id) {
		return personRepository.findOne(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}

}
