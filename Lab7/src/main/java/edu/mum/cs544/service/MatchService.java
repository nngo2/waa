package edu.mum.cs544.service;

import edu.mum.cs544.model.FriendlyMatch;
import edu.mum.cs544.model.MatchDto;
import edu.mum.cs544.model.TournamentMatch;

public interface MatchService {
	MatchDto getTournamentMatch(long id);
	Iterable<TournamentMatch> getAllTournamentMatches();
	void addTournamentMatch(MatchDto match);
	void updateTournamentMatch(long id, MatchDto match);
	Iterable<FriendlyMatch> getAllFriendlyMatches();
	void addFriendlyMatch(MatchDto match);
}
