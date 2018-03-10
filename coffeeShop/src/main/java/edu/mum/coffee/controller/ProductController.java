package edu.mum.coffee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.model.ProductDto;
import edu.mum.coffee.service.ProductServiceClient;

@Controller
public class ProductController {
	@Autowired
	private ProductServiceClient productServiceClient;
	
	@RequestMapping(value="/admin/products", method = RequestMethod.GET)
	public ModelAndView getAllProducts() {
		ModelAndView modelAndView = new ModelAndView();		
		List<ProductDto> products = productServiceClient.getAllProdcuts();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("admin/product");
		return modelAndView;
	}
}
