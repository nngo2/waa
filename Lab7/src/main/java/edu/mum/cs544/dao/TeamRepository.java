package edu.mum.cs544.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import edu.mum.cs544.model.Team;

@Transactional
public interface TeamRepository extends CrudRepository<Team,Integer> {

}
