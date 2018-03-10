package edu.mum.coffee.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.model.ProductDto;

@Service
public class ProductServiceClient {
	private static final String SERVICE_PATH = "products";	
	
	@Value("${rest.endpoint}")
	private String restUri;
	
	private RestTemplate restTemplate;
	
	public ProductServiceClient() {
		restTemplate = new RestTemplate();
	}
	
	public List<ProductDto> getAllProdcuts() {
		ResponseEntity<ProductDto[]> responseEntity = restTemplate.getForEntity(restUri + SERVICE_PATH, ProductDto[].class);
		ProductDto[] products = responseEntity.getBody();
		return Arrays.asList(products);
	}
	
	public ProductDto createProduct(ProductDto product) {
		HttpEntity<ProductDto> request = new HttpEntity<ProductDto>(product);
		return restTemplate.postForObject(restUri + SERVICE_PATH, request, ProductDto.class);
	}
	
	public ProductDto findById(int id) {
		return restTemplate.getForObject(restUri + SERVICE_PATH + "/" + id, ProductDto.class);
	}
}
