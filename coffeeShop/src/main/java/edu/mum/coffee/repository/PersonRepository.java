package edu.mum.coffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.coffee.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	public Person findByEmail(String email);	
}
