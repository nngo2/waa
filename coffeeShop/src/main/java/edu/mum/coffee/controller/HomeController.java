package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.mum.coffee.model.ProductDto;
import edu.mum.coffee.service.ProductServiceClient;

@Controller
public class HomeController {
	@Autowired
	private ProductServiceClient productServiceClient;
	
	@GetMapping({"/", "/index", "/home"})
	public String homePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean hasAdminRole = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
		if (hasAdminRole) {
			model.addAttribute("userName", "Welcome " + auth.getName());
			model.addAttribute("adminMessage","Welcome to admin home");
			return "admin/home";
		}
		
		List<ProductDto> products = productServiceClient.getAllProdcuts();
		model.addAttribute("products", products);
		return "home";
	}
}
