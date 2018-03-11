package edu.mum.coffee.controller;

import java.util.List;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.model.ProductDto;
import edu.mum.coffee.service.ProductServiceClient;

@Controller
public class ProductController {
	@Autowired
	private ServletContext context;

	@Autowired
	private ProductServiceClient productServiceClient;

	@RequestMapping(value = "/admin/products", method = RequestMethod.GET)
	public ModelAndView getAllProducts() {
		ModelAndView modelAndView = new ModelAndView();
		List<ProductDto> products = productServiceClient.getAllProdcuts();
		modelAndView.addObject("products", products);
		modelAndView.setViewName("admin/product");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/products/create", method = RequestMethod.GET)
	public String registration(@ModelAttribute("product") ProductDto product, Model model) {
		model.addAttribute("productTypes", ProductType.values());
		return "admin/product_details";
	}

	@RequestMapping(value = "/admin/products/create", method = RequestMethod.POST)
	public ModelAndView createProduct(@RequestParam("file") MultipartFile file,
			@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/product_details");
		} else {
			// Save file
			try {
				// get physical path
				String savePath = context.getRealPath("") + File.separator + "resources/images";
				String uniqueName = getUniqueFileName(file.getOriginalFilename());
				product.setProductImage("/resources/images/" + uniqueName);

				byte[] bytes = file.getBytes();
				Path path = Paths.get(savePath + "/" + uniqueName);
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// then save form data
			productServiceClient.createProduct(product);
			modelAndView.addObject("successMessage", "Product has been create successfully");
			modelAndView.setViewName("admin/product_details");
		}

		return modelAndView;
	}

	private String getUniqueFileName(String name) {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "") + "_" + name;
	}

}
