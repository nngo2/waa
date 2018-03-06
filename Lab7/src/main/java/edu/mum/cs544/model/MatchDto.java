package edu.mum.cs544.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
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
	
	@NotBlank
	private int stadiumId;
	
	@NotBlank
	private int homeScore;
	
	@NotBlank
	private int visitorScore;
	
	@NotBlank
	private int visitorTeamId;
	
	@NotBlank
	private int homeTeamId;

	private int homePoints;
	private int visitorPoints;
	
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
	
}
