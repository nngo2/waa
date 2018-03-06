package edu.mum.cs544.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "match_type", discriminatorType = DiscriminatorType.STRING)
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long matchKey;
	
	private LocalDate date;
	private LocalTime startTime;
	
	@ManyToOne
	@JoinColumn(name="stadiumId")	
	private Stadium stadium;
	
	private int homeScore;
	private int visitorScore;
	
	@ManyToOne
	@JoinColumn(name="visitorTeamId")
	private Team visitorTeam;
	
	@ManyToOne
	@JoinColumn(name="homeTeamId")
	private Team homeTeam;
	
	public Match() {
		super();
	}

	public Match(LocalDate date, LocalTime startTime, Stadium stadium, int homeScore, int visitorScore,
			Team visitorTeam, Team homeTeam) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.stadium = stadium;
		this.homeScore = homeScore;
		this.visitorScore = visitorScore;
		this.visitorTeam = visitorTeam;
		this.homeTeam = homeTeam;
	}
	
	public long getMatchKey() {
		return matchKey;
	}
	public void setMatchKey(long matchKey) {
		this.matchKey = matchKey;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public Stadium getStadium() {
		return stadium;
	}
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getVisitorScore() {
		return visitorScore;
	}
	public void setVisitorScore(int visitorScore) {
		this.visitorScore = visitorScore;
	}
	public Team getVisitorTeam() {
		return visitorTeam;
	}
	public void setVisitorTeam(Team visitorTeam) {
		this.visitorTeam = visitorTeam;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
}
