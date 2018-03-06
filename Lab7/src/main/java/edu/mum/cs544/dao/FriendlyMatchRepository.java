package edu.mum.cs544.dao;

import javax.transaction.Transactional;

import edu.mum.cs544.model.FriendlyMatch;

@Transactional
public interface FriendlyMatchRepository extends MatchBaseRepository<FriendlyMatch> {

}
