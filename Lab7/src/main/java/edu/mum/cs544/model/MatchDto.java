package edu.mum.cs544.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class MatchDto {
	private long id;
	
	@NotNull
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate date;
	
	@NotNull
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime startTime;
	
	@NotNull
	private int stadiumId;
	
	@NotNull
	@Min(value=0)
	private int homeScore;
	
	@NotNull
	@Min(value=0)
	private int visitorScore;
	
	@NotNull
	private int visitorTeamId;
	
	@NotNull
	private int homeTeamId;

	@Min(value=0)
	private int homePoints;

	@Min(value=0)
	private int visitorPoints;
	
	private int awardId;
	
	public MatchDto() {
		super();
	}

	public MatchDto(long id, LocalDate date, LocalTime startTime, int stadiumId, int homeScore, int visitorScore,
			int visitorTeamId, int homeTeamId, int homePoints, int visitorPoints) {
		super();
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.stadiumId = stadiumId;
		this.homeScore = homeScore;
		this.visitorScore = visitorScore;
		this.visitorTeamId = visitorTeamId;
		this.homeTeamId = homeTeamId;
		this.homePoints = homePoints;
		this.visitorPoints = visitorPoints;
	}
	
	public MatchDto(long id, LocalDate date, LocalTime startTime, int stadiumId, int homeScore, int visitorScore,
			int visitorTeamId, int homeTeamId, int homePoints, int visitorPoints, int awardId) {
		this(id, date, startTime, stadiumId, homeScore, visitorScore, visitorTeamId, homeTeamId, homePoints, visitorPoints);
		this.awardId = awardId;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getStadiumId() {
		return stadiumId;
	}
	public void setStadiumId(int stadiumId) {
		this.stadiumId = stadiumId;
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
	public int getVisitorTeamId() {
		return visitorTeamId;
	}
	public void setVisitorTeamId(int visitorTeamId) {
		this.visitorTeamId = visitorTeamId;
	}
	public int getHomeTeamId() {
		return homeTeamId;
	}
	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
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

	public int getAwardId() {
		return awardId;
	}

	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}
}
