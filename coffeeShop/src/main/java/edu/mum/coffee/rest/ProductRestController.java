package edu.mum.coffee.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;

@RestController
public class ProductRestController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/api/products", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		return  productService.getAllProduct();
	}
	
	@RequestMapping(value="/api/products/{id}", method = RequestMethod.GET)
	public Product getProductById(@PathVariable("id") int id) {
		return  productService.getProduct(id);
	}
	
	@RequestMapping(value="/api/products", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {
		return  productService.save(product);
	}
	
	@RequestMapping(value="/api/products", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		return  productService.save(product);
	}
	
	@RequestMapping(value="/api/products/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") int id) {
		Product product = productService.getProduct(id);
		if (product != null) {
			productService.delete(product);
		}
	}
}
