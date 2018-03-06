package edu.mum.cs544.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import edu.mum.cs544.model.Match;

@NoRepositoryBean
public interface MatchBaseRepository<T extends Match> extends CrudRepository<T, Long>{
}
