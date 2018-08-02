package demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@CrossOrigin(maxAge = 4800) 
@RestController
public class UiApplication {

	List<Customer> custList = new ArrayList<Customer>();
	List<Order> orderList = new ArrayList<Order>();
	
	@PostConstruct
	public void init(){
		
		custList.add(new Customer("1", "Ann"));
		custList.add(new Customer("2", "Anita"));
		custList.add(new Customer("3", "Bowie"));
		custList.add(new Customer("4", "Bonnie"));
		custList.add(new Customer("5", "Cat"));
		custList.add(new Customer("6", "Cathy"));
		custList.add(new Customer("7", "Donnie"));
		custList.add(new Customer("8", "Dicken"));
		custList.add(new Customer("9", "Elaine"));
		custList.add(new Customer("10", "Ethen"));
		custList.add(new Customer("11", "Fanny"));
		custList.add(new Customer("12", "Francise"));
		custList.add(new Customer("13", "Gloria"));
		custList.add(new Customer("14", "Gary"));
		custList.add(new Customer("15", "Henry"));
		custList.add(new Customer("16", "Haliary"));
		custList.add(new Customer("17", "Ian"));
		custList.add(new Customer("18", "Ivan"));
		custList.add(new Customer("19", "Jackson"));
		custList.add(new Customer("20", "Johnny"));
		
		for(int i=1;i<=20;i++){
			orderList.add(new Order(Integer.toString(i),"watch"+i, new BigDecimal(i*10000)));
		}
			
	}
	
	
	@RequestMapping("/resource")
	public Map<String,Object> home() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}
	
	@RequestMapping("/login/{username}/{pwd}")
	public User login(@PathVariable("username")String username, @PathVariable("pwd")String pwd) {
		if(username.equals("travis")&&pwd.equals("1234")){
			return new User("1", "travis", "1234");
		}
		return null;
	}

	@RequestMapping("/customerSearch/{custName}")
	public List<Customer> customerSearch(@PathVariable("custName")String custName) {
		
		List<Customer> result = new ArrayList<Customer>();
		
		custList.forEach(cust->{
			if(cust.getName().toUpperCase().startsWith(custName.toUpperCase())){
				result.add(cust);
			}
		});
		
		return result;
	}
	
	@RequestMapping("/getOrder/{id}")
	public List<Order> getOrder(@PathVariable("id")String id) {
		
		List<Order> result = new ArrayList<Order>();
		
		Random rand = new Random(20);
		
		for(int i=0;i<3;i++){
			result.add(orderList.get(rand.nextInt()));
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

}
