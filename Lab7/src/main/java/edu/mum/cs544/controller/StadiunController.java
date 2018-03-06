package edu.mum.cs544.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.cs544.service.StadiumService;

@Controller
public class StadiunController {

	@Resource
	private StadiumService stadiumService;
	
	@RequestMapping(value="/stadiums", method=RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("stadiums", stadiumService.getAllStadiums());
		return "stadiumList";
	}
	
}
