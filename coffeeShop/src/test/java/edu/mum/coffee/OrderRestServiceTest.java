package edu.mum.coffee;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.repository.UserRepository;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRestServiceTest {
	@Value("${rest.endpoint}")
	private String restUri;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PersonService personService;
	@Autowired	
	private UserRepository userRepository;
	@Autowired
	private ProductService productService;	

	private Product testProduct;
	private Person testPerson;
	private Order testOrder;
	
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() throws Exception {
		restTemplate = new RestTemplate();
		
		testPerson = new Person();
		testPerson.setEmail("test@email.com");
		testPerson.setPassword("password");
		testPerson.setEnable(true);
		testPerson.setFirstName("Test");
		testPerson.setLastName("Test");
		testPerson.setPhone("111888777");
		testPerson.setAddress(new Address());
		testPerson.getAddress().setZipcode("52557");
		testPerson.getAddress().setCity("Fairfield");
		testPerson.getAddress().setState("IA");
		testPerson.getAddress().setCountry("USA");
		testPerson = personService.savePerson(testPerson);
		
		testProduct = new Product();
		testProduct.setProductName("TEST-productName");
		testProduct.setDescription("TEST-Item Description");
		testProduct.setPrice(100.0);
		testProduct = productService.save(testProduct);
	}
	
	@After
	public void tearDown() throws Exception {
		if (testOrder.getId() > 0) {
			orderService.delete(testOrder);
		}
		
		if (testProduct.getId() > 0) {
			productService.delete(testProduct);
		}
		
		if (testPerson.getId() > 0) {
			User user = userRepository.findByEmail(testPerson.getEmail());
			userRepository.delete(user);
			personService.removePerson(testPerson);
		}
	}
	
	@Test
	public void testSave() {
		
		Orderline line = new Orderline();
		line.setProduct(testProduct);
		line.setQuantity(1);
		testOrder = new Order();
		testOrder.setOrderDate(new Date());
		testOrder.setPerson(testPerson);
		testOrder.addOrderLine(line);
		
		Order createdOrder = createOrder(testOrder);
		Order saved = orderService.findById(createdOrder.getId());
		
		if (!compareOrder(testOrder, saved)) {
			fail("Not storing or retrieving Order");
		}
		
		testOrder = saved;
	}
	
	@Test
	public void testGet() {
		Orderline line = new Orderline();
		line.setProduct(testProduct);
		line.setQuantity(1);
		testOrder = new Order();
		testOrder.setOrderDate(new Date());
		testOrder.setPerson(testPerson);
		testOrder.addOrderLine(line);
		
		testOrder = orderService.save(testOrder);
		
		Order createdOrder = getOrder(testOrder.getId());
		
		if (createdOrder == null) {
			fail("Cannot get Order");
		}
		
	}
	
	private Order createOrder(Order order) {
		HttpEntity<Order> request = new HttpEntity<Order>(order);
		return restTemplate.postForObject(restUri + "orders", request, Order.class);
	}
	
	private Order getOrder(int id) {
		return restTemplate.getForObject(restUri + "orders/" + id, Order.class);
	}
	
	private boolean compareOrder(Order a, Order b) {
		if ((a == null) || (b == null)) {
			return false;
		}
		if (a.getPerson().getId() != b.getPerson().getId()) {
			return false;
		}
		if (a.getTotalAmount() != b.getTotalAmount()) {
			return false;
		}
		return true;
	}
}
