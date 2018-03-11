package edu.mum.coffee.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
@SessionAttributes(value = { "cart", "person" })
public class CartController {
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/cart/addorder", method = RequestMethod.POST)
	public String placeOrder(ModelMap map, SessionStatus status) {
		if (map.containsAttribute("cart")) {
			Order order = (Order) map.get("cart");
			order.setOrderDate(new Date());
			orderService.save(order);
			status.setComplete();
		}
		
		return "redirect:/myorders";
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView getCart(ModelMap map) {
		if (!map.containsAttribute("cart")) {
			map.addAttribute("cart", new Order());
		}
		
		if (!map.containsAttribute("person")) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName(); // get logged in username
			Person person = personService.findByEmail(name);
			map.addAttribute("person", person);
		}

		Order cart = (Order) map.get("cart");
		cart.setPerson((Person)map.get("person"));
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("cart");
		return modelAndView;
	}
	
	@RequestMapping(value = "/cart/add/{id}", method = RequestMethod.POST)
	public ModelAndView addToCart(@PathVariable int id, ModelMap map) {
		if (!map.containsAttribute("cart")) {
			map.addAttribute("cart", new Order());
		}
		
		if (!map.containsAttribute("person")) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName(); // get logged in username
			Person person = personService.findByEmail(name);
			map.addAttribute("person", person);
		}

		Order cart = (Order) map.get("cart");
		cart.setPerson((Person)map.get("person"));
		
		Product product = productService.getProduct(id);
		Orderline line = new Orderline();
		line.setProduct(product);
		line.setQuantity(1);
		cart.addOrderLine(line);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cart", cart);
		modelAndView.setViewName("cart");
		return modelAndView;
	}
}
