package edu.mum.cs544.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Stadium {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stadiumKey;
	
	@NameFormat
	private String name;
	
	private String city;
	
	@Column(name="state")
	private String state;
	
	@OneToMany
	@JoinColumn(name="matchId")
	private List<Match> matches;
	
	public Stadium() {
		super();
	}

	public Stadium(int stadiumKey, String name, String city, String state) {
		super();
		this.stadiumKey = stadiumKey;
		this.name = name;
		this.city = city;
		this.state = state;
	}
	
	public int getStadiumKey() {
		return stadiumKey;
	}
	public void setStadiumKey(int stadiumKey) {
		this.stadiumKey = stadiumKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
}
