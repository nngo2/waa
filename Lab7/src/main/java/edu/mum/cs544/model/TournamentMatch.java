package edu.mum.cs544.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TM")
public class TournamentMatch extends Match {
	private int homePoints;
	private int visitorPoints;
		
	public TournamentMatch() {
		super();
	}
	
	public TournamentMatch(LocalDate date, LocalTime startTime, Stadium stadium, int homeScore, int visitorScore,
			Team visitorTeam, Team homeTeam, int homePoints, int visitorPoints) {
		super(date, startTime, stadium, homeScore, visitorScore, visitorTeam, homeTeam);
		this.homePoints = homePoints;
		this.visitorPoints = visitorPoints;
	}
	
	public int getHomePoints() {
		return homePoints;
	}
	public void setHomePoints(int homePoints) {
		this.homePoints = homePoints;
	}
	public int getVisitorPoints() {
		return visitorPoints;
	}
	public void setVisitorPoints(int visitorPoints) {
		this.visitorPoints = visitorPoints;
	}
}
