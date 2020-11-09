package shop.bean;

import shop.entity.Product;

public class Cart {

	private int quantity;
	private double totalPrice;
	private Product product;
	
	
	public Cart() {
		
	}
	
	public Cart(int quantity, double totalPrice, Product product) {
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
