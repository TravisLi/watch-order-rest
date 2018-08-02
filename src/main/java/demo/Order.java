package demo;

import java.math.BigDecimal;

public class Order {

	private String id;
	private String product;
	private BigDecimal price;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Order(String id, String product, BigDecimal price) {
		super();
		this.id = id;
		this.product = product;
		this.price = price;
	}
	
}
