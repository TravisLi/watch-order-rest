package demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@CrossOrigin(maxAge = 4800, allowCredentials = "false") 
@RestController
@RequestMapping("/api")
public class UiApplication {

	private static Logger logger = LoggerFactory.getLogger(UiApplication.class);

	List<Customer> custList = new ArrayList<Customer>();
	List<demo.Order> orderList = new ArrayList<demo.Order>();
	List<Product> productList = new ArrayList<Product>();

	/*@Configuration
	@Order(SecurityProperties.IGNORED_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			.httpBasic()
			.and()
			.authorizeRequests()
			.antMatchers("/index.html", "/", "/home", "/login").permitAll()
			.anyRequest().authenticated();
		}
	}*/
	
	@PostConstruct
	public void init(){
		logger.info("init");
		custList.add(new Customer("1", "李小明"));
		custList.add(new Customer("2", "李大明"));
		custList.add(new Customer("3", "李小令"));
		custList.add(new Customer("4", "李大力"));
		custList.add(new Customer("5", "陳小明"));
		custList.add(new Customer("6", "陳大明"));
		custList.add(new Customer("7", "陳小令"));
		custList.add(new Customer("8", "陳大力"));
		custList.add(new Customer("9", "王小明"));
		custList.add(new Customer("10", "王大明"));
		custList.add(new Customer("11", "王小令"));
		custList.add(new Customer("12", "王大力"));
		custList.add(new Customer("13", "何小明"));
		custList.add(new Customer("14", "何大明"));
		custList.add(new Customer("15", "何小令"));
		custList.add(new Customer("16", "何大力"));
		custList.add(new Customer("17", "張小明"));
		custList.add(new Customer("18", "張大明"));
		custList.add(new Customer("19", "張小令"));
		custList.add(new Customer("20", "張大力"));

		for(int i=1;i<=20;i++){
			productList.add(new demo.Product(Integer.toString(i),"勞力士"+i, new BigDecimal(i*10000)));
		}
		
		for(int i=1;i<=20;i++){
			
			Order order = new Order();
			order.setId(Integer.toString(i));
			
			Random rand = new Random();
			
			int day = rand.nextInt(29)+1;
			
			int month = rand.nextInt(12)+1;
			
			Calendar cal = Calendar.getInstance();
			
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.MONTH, month);
			
			order.setDate(cal.getTime());
			
			Integer discount = (rand.nextInt(5) + 1) * 10;
			order.setDiscount(discount);
			
			int productNo = rand.nextInt(3)+1;
			
			BigDecimal total = new BigDecimal(0);
			
			for(int j=0;j<productNo;j++){
				Product product = productList.get(rand.nextInt(20));
				total = total.add(product.getPrice());
				order.getProducts().add(product);
			}
			
			total = total.multiply(new BigDecimal(discount));
			
			logger.info("total = " + total);
			order.setTotal(total);
			
			orderList.add(order);
		}

	}


	@RequestMapping(value="/resource",method = RequestMethod.GET)
	public Map<String,Object> home() {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		return model;
	}

	@RequestMapping(value="/login/{username}/{pwd}",method = {RequestMethod.OPTIONS,RequestMethod.GET})
	public User login(@PathVariable("username")String username, @PathVariable("pwd")String pwd) {

		logger.info("login");

		if(username.equals("test")&&pwd.equals("1234")){
			return new User("1", "test", "1234");
		}
		return null;
	}

	@RequestMapping(value="/customerSearch/{custName}", method = RequestMethod.GET)
	public List<Customer> customerSearch(@PathVariable("custName")String custName) {

		List<Customer> result = new ArrayList<Customer>();

		custList.forEach(cust->{
			if(cust.getName().toUpperCase().startsWith(custName.toUpperCase())){
				logger.info("Cust Name = " + cust.getName());
				result.add(cust);
			}
		});

		return result;
	}

	@RequestMapping(value="/order/{id}", method = RequestMethod.GET)
	public List<demo.Order> getOrder(@PathVariable("id")String id) {

		logger.info("GetOrder with id=" + id);
		
		List<demo.Order> result = new ArrayList<demo.Order>();

		Random rand = new Random();

		for(int i=0;i<3;i++){
			result.add(orderList.get(rand.nextInt(20)));
		}

		return result;
	}

	public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

}
