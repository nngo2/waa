package edu.mum.cs544.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs544.dao.StadiumRepository;
import edu.mum.cs544.model.Stadium;

@Service
public class StadiumServiceImpl implements StadiumService {
	
	@Autowired
	private StadiumRepository stadiumRepository;
	
	public StadiumServiceImpl(StadiumRepository stadiumRepository) {
		super();
		this.stadiumRepository = stadiumRepository;
	}

	@Override
	public Iterable<Stadium> getAllStadiums() {
		return stadiumRepository.findAll();
	}

	public StadiumRepository getStadiumRepository() {
		return stadiumRepository;
	}

	public void setStadiumRepository(StadiumRepository stadiumRepository) {
		this.stadiumRepository = stadiumRepository;
	}

}
