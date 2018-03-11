package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;

@Controller
public class MyOrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PersonService personService;	
	
	@RequestMapping(value = "/myorders", method = RequestMethod.GET)
	public ModelAndView getOrder(ModelMap map) {
		if (!map.containsAttribute("person")) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName(); // get logged in username
			Person person = personService.findByEmail(name);
			map.addAttribute("person", person);
		}

		List<Order> orders = orderService.findByPerson((Person)map.get("person"));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orders", orders);
		modelAndView.setViewName("my_order");
		return modelAndView;
	}
	
	@RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
	public ModelAndView getOrderDetails(@PathVariable("id") int id, ModelMap map) {
		Order order = orderService.findById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("order", order);
		modelAndView.setViewName("order_detail");
		return modelAndView;
	}
}
