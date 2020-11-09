package shop.function;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import shop.bean.Cart;
import shop.entity.Product;

@Transactional
public class CartFunc {
	@Autowired
	SessionFactory factory;

	public HashMap<Integer, Cart> addCart(int id, HashMap<Integer, Cart> cart) {
		Cart itemCart = new Cart();
		Session session = factory.getCurrentSession();
		String hql = "FROM Product WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		if(product != null) {
			itemCart.setProduct(product);
			itemCart.setQuantity(1);
			itemCart.setTotalPrice(product.getPrice());
		}
		cart.put(id, itemCart);
		return cart;
	}
	
	public HashMap<Integer, Cart> editCart(int id, int quantity, HashMap<Integer, Cart> cart) {
		if(cart == null) {
			return cart;
		}
		Cart itemCart = new Cart();
		if(cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuantity(quantity);
			double totalPrice = quantity * itemCart.getProduct().getPrice();
			itemCart.setTotalPrice(totalPrice);
		}
		cart.put(id, itemCart);
		return cart;
	}
	
	public HashMap<Integer, Cart> deleteCart(int id, HashMap<Integer, Cart> cart) {
		if(cart == null) {
			return cart;
		}
		if(cart.containsKey(id)) {
			cart.remove(id);
		}
		return cart;
	}
	
	public int totalQuantity(HashMap<Integer, Cart> cart) {
		int totalQuantity = 0;
		for(Map.Entry<Integer, Cart> itemCart : cart.entrySet()) {
			totalQuantity += itemCart.getValue().getQuantity();
		}
		return totalQuantity;
	}
	
	public double totalPrice(HashMap<Integer, Cart> cart) {
		double totalPrice = 0;
		for(Map.Entry<Integer, Cart> itemCart : cart.entrySet()) {
			totalPrice += itemCart.getValue().getTotalPrice();
		}
		return totalPrice;
	}
}
