package edu.mum.cs544.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.cs544.service.TeamService;

@Controller
public class TeamController {
	@Resource
	private TeamService teamService;
	
	@RequestMapping(value="/teams", method=RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("teams", teamService.getAllTeams());
		return "teamList";
	}
	
}
