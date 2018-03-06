package edu.mum.cs544.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FM")
public class FriendlyMatch extends Match {
	private AwardType award;

	public FriendlyMatch() {
		super();
	}

	public FriendlyMatch(LocalDate date, LocalTime startTime, Stadium stadium, int homeScore, int visitorScore,
			Team visitorTeam, Team homeTeam, AwardType award) {
		super(date, startTime, stadium, homeScore, visitorScore, visitorTeam, homeTeam);
		this.award = award;
	}

	public AwardType getAward() {
		return award;
	}

	public void setAward(AwardType award) {
		this.award = award;
	}
}
