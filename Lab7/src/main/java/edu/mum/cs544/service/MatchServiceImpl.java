package edu.mum.cs544.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs544.dao.FriendlyMatchRepository;
import edu.mum.cs544.dao.StadiumRepository;
import edu.mum.cs544.dao.TeamRepository;
import edu.mum.cs544.dao.TournamentMatchRepository;
import edu.mum.cs544.model.AwardType;
import edu.mum.cs544.model.FriendlyMatch;
import edu.mum.cs544.model.MatchDto;
import edu.mum.cs544.model.Stadium;
import edu.mum.cs544.model.Team;
import edu.mum.cs544.model.TournamentMatch;

@Service
public class MatchServiceImpl implements MatchService {
	@Autowired
	private TournamentMatchRepository tournamentMatchRepository;
	
	@Autowired
	private FriendlyMatchRepository friendlyMatchRepository;
	
	@Autowired 
	private TeamRepository teamRepository;
	
	@Autowired 
	private StadiumRepository stadiumRepository;
		
	public MatchServiceImpl() {
		super();
	}

	public MatchServiceImpl(TournamentMatchRepository tournamentMatchRepository,
			FriendlyMatchRepository friendlyMatchRepository) {
		super();
		this.tournamentMatchRepository = tournamentMatchRepository;
		this.friendlyMatchRepository = friendlyMatchRepository;
	}

	public TournamentMatchRepository getTournamentMatchRepository() {
		return tournamentMatchRepository;
	}

	public void setTournamentMatchRepository(TournamentMatchRepository tournamentMatchRepository) {
		this.tournamentMatchRepository = tournamentMatchRepository;
	}

	public FriendlyMatchRepository getFriendlyMatchRepository() {
		return friendlyMatchRepository;
	}

	public void setFriendlyMatchRepository(FriendlyMatchRepository friendlyMatchRepository) {
		this.friendlyMatchRepository = friendlyMatchRepository;
	}

	@Override
	public Iterable<TournamentMatch> getAllTournamentMatches() {
		return tournamentMatchRepository.findAll();
	}

	@Override
	public Iterable<FriendlyMatch> getAllFriendlyMatches() {
		return friendlyMatchRepository.findAll();
	}

	@Override
	public void addTournamentMatch(MatchDto match) {
		Optional<Stadium> stadium = stadiumRepository.findById(match.getStadiumId());
		Optional<Team> vTeam = teamRepository.findById(match.getVisitorTeamId());
		Optional<Team> hTeam = teamRepository.findById(match.getHomeTeamId());
		
		TournamentMatch tMatch = new TournamentMatch(
				match.getDate(),
				match.getStartTime(),
				stadium.get(),
				match.getHomeScore(),
				match.getVisitorScore(),
				vTeam.get(),
				hTeam.get(),
				match.getHomePoints(),
				match.getVisitorPoints());
		
		tournamentMatchRepository.save(tMatch);
	}

	@Override
	public void addFriendlyMatch(MatchDto match) {
		Optional<Stadium> stadium = stadiumRepository.findById(match.getStadiumId());
		Optional<Team> vTeam = teamRepository.findById(match.getVisitorTeamId());
		Optional<Team> hTeam = teamRepository.findById(match.getHomeTeamId());
		
		FriendlyMatch tMatch = new FriendlyMatch(
				match.getDate(),
				match.getStartTime(),
				stadium.get(),
				match.getHomeScore(),
				match.getVisitorScore(),
				vTeam.get(),
				hTeam.get(),
				AwardType.valueOf(match.getAwardId()));
		
		friendlyMatchRepository.save(tMatch);
	}

	@Override
	public MatchDto getTournamentMatch(long id) {
		Optional<TournamentMatch> match = tournamentMatchRepository.findById(id);
		if (!match.isPresent()) {
			return null;
		}
		
		TournamentMatch rMatch = match.get();
		MatchDto dto = new MatchDto(
				rMatch.getMatchKey(),
				rMatch.getDate(),
				rMatch.getStartTime(),
				rMatch.getStadium().getStadiumKey(),
				rMatch.getHomePoints(),
				rMatch.getVisitorScore(),
				rMatch.getHomeTeam().getTeamKey(),
				rMatch.getVisitorTeam().getTeamKey(),
				rMatch.getHomePoints(),
				rMatch.getVisitorPoints()
			);
		
		return dto;
	}

	@Override
	public void updateTournamentMatch(long id, MatchDto match) {
		Optional<TournamentMatch> oMatch = tournamentMatchRepository.findById(id);
		if (!oMatch.isPresent()) {
			return;
		}
		
		TournamentMatch uMatch = oMatch.get();
		
		Stadium stadium = stadiumRepository.findById(match.getStadiumId()).get();
		Team vTeam = teamRepository.findById(match.getVisitorTeamId()).get();
		Team hTeam = teamRepository.findById(match.getHomeTeamId()).get();
		
		uMatch.setDate(match.getDate());
		uMatch.setHomePoints(match.getHomePoints());
		uMatch.setHomeScore(match.getHomeScore());
		uMatch.setHomeTeam(hTeam);
		uMatch.setStadium(stadium);
		uMatch.setStartTime(match.getStartTime());
		uMatch.setVisitorPoints(match.getVisitorPoints());
		uMatch.setVisitorScore(match.getVisitorScore());
		uMatch.setVisitorTeam(vTeam);
		
		tournamentMatchRepository.save(uMatch);
	}
}
