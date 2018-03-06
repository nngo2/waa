package edu.mum.cs544.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs544.dao.TeamRepository;
import edu.mum.cs544.model.Team;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
	public TeamServiceImpl(TeamRepository teamRepository) {
		super();
		this.teamRepository = teamRepository;
	}

	public TeamRepository getTeamRepository() {
		return teamRepository;
	}

	public void setTeamRepository(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public Iterable<Team> getAllTeams() {
		return teamRepository.findAll();
	}
}
