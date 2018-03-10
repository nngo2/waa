package edu.mum.coffee.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.model.PersonDto;

@Service
public class PersonServiceClient {
	private static final String PERSON_SERVICE_PATH = "persons";	
	
	@Value("${rest.endpoint}")
	private String restUri;
	
	private RestTemplate restTemplate;
	
	public PersonServiceClient() {
		restTemplate = new RestTemplate();
	}
	
	public List<PersonDto> getAllPersons() {
		ResponseEntity<PersonDto[]> responseEntity = restTemplate.getForEntity(restUri + PERSON_SERVICE_PATH, PersonDto[].class);
		PersonDto[] persons = responseEntity.getBody();
		return Arrays.asList(persons);
	}
	
	public PersonDto createPerson(PersonDto person) {
		HttpEntity<PersonDto> request = new HttpEntity<PersonDto>(person);
		return restTemplate.postForObject(restUri + PERSON_SERVICE_PATH, request, PersonDto.class);
	}
	
	public PersonDto findByEmail(String email) {
		return restTemplate.getForObject(restUri + PERSON_SERVICE_PATH + "/findbyemail?email=" + email, PersonDto.class);
	}
}
