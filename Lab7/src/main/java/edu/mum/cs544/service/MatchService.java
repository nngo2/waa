package edu.mum.cs544.service;

import edu.mum.cs544.model.FriendlyMatch;
import edu.mum.cs544.model.MatchDto;
import edu.mum.cs544.model.TournamentMatch;

public interface MatchService {
	Iterable<TournamentMatch> getAllTournamentMatches();
	void addTournamentMatch(TournamentMatch match);
	Iterable<FriendlyMatch> getAllFriendlyMatches();
	void addFriendlyMatch(FriendlyMatch match);
	void addMatch(MatchDto match);
}
