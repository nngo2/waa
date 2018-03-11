package edu.mum.coffee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.ProductType;
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
	
	@RequestMapping(value="/admin/products/create", method = RequestMethod.GET)
	public String registration(@ModelAttribute("product") ProductDto product, Model model){
		model.addAttribute("productTypes", ProductType.values());
		return "admin/product_details";
	}	
	
	@RequestMapping(value = "/admin/products/create", method = RequestMethod.POST)
	public ModelAndView createProduct(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();		
		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/product_details");
		} else {
			productServiceClient.createProduct(product);
			modelAndView.addObject("successMessage", "Product has been create successfully");
			modelAndView.setViewName("admin/product_details");
		}
		
		return modelAndView;
	}

}
