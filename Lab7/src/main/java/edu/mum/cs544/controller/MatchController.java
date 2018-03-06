package edu.mum.cs544.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs544.model.MatchDto;
import edu.mum.cs544.service.MatchService;
import edu.mum.cs544.service.StadiumService;
import edu.mum.cs544.service.TeamService;

@Controller
public class MatchController {
	@Resource
	private MatchService matchService;
	
	@Resource
	private StadiumService stadiumService;
	
	@Resource
	private TeamService teamService;
	
	
	@RequestMapping(value="/matches", method=RequestMethod.GET)
	public String getAll(@RequestParam("matchtype") String matchType, Model model) {
		
		if ("TM".equalsIgnoreCase(matchType)) {
			model.addAttribute("matches", matchService.getAllTournamentMatches());
		} else {
			model.addAttribute("matches", matchService.getAllFriendlyMatches());
		}

		return "matchList";
	}
	
	@RequestMapping(value= {"/addMatch", "addMatch"}, method=RequestMethod.GET)
	public String addMatch(@ModelAttribute("matchDto") MatchDto match, Model model) {
		model.addAttribute("stadiumList", stadiumService.getAllStadiums());
		model.addAttribute("teamList", teamService.getAllTeams());
		return "addMatch";
	}
	
	@RequestMapping(value="/matches", method=RequestMethod.POST)
	public String add(@Valid @ModelAttribute("matchDto") MatchDto match, Model model, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("stadiumList", stadiumService.getAllStadiums());
			model.addAttribute("teamList", teamService.getAllTeams());			
			return "/addMatch";
		} else {
			matchService.addMatch(match);
			return "redirect:/matches?matchtype=TM";			
		}
	}
}
