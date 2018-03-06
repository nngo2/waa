package edu.mum.cs544.dao;

import javax.transaction.Transactional;

import edu.mum.cs544.model.TournamentMatch;

@Transactional
public interface TournamentMatchRepository extends MatchBaseRepository<TournamentMatch>{

}
