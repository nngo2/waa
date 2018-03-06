package edu.mum.cs544.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int palyerId;

	private String firstName;
	private String lastName;
	
	public Player() {
		super();
	}
	
	public Player(int palyerId, String firstName, String lastName) {
		super();
		this.palyerId = palyerId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getPalyerId() {
		return palyerId;
	}
	public void setPalyerId(int palyerId) {
		this.palyerId = palyerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
