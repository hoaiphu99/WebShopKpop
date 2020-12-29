package shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.bean.Cart;
import shop.entity.Product;

@Controller
@Transactional
public class CartController {

	@Autowired
	SessionFactory factory;
	
	@RequestMapping("gio-hang")
	public String cart(ModelMap model) {
		
		return "client/cart";
	}
	
	@RequestMapping(value="add-cart/{id}")
	public String addCart(HttpSession session, HttpServletRequest request, @PathVariable("id") Integer id) {
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<Integer, Cart>();
		}
		cart = addCart(id, cart);
		session.setAttribute("cart", cart);
		session.setAttribute("totalQuantityCart", totalQuantity(cart));
		session.setAttribute("totalPriceCart", totalPrice(cart));
		return "redirect:" + request.getHeader("Referer").replaceAll("http://localhost:9999/Project-200926/", "/"); // request.getHeader("Referer") lấy back link
	}
	
	@RequestMapping(value="add-cart/{id}/{quantity}")
	public String addCartwithQuantity(HttpSession session, HttpServletRequest request, @PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity) {
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<Integer, Cart>();
		}
		cart = addCartwithQuantity(id, quantity, cart);
		session.setAttribute("cart", cart);
		session.setAttribute("totalQuantityCart", totalQuantity(cart));
		session.setAttribute("totalPriceCart", totalPrice(cart));
		return "redirect:" + request.getHeader("Referer").replaceAll("http://localhost:9999/Project-200926/", "/"); // request.getHeader("Referer") lấy back link
	}
	
	@RequestMapping(value="edit-cart/{id}/{quantity}")
	public String editCart(HttpSession session, HttpServletRequest request, @PathVariable("id") Integer id, @PathVariable("quantity") Integer quantity) {
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<Integer, Cart>();
		}
		cart = editCart(id, quantity, cart);
		session.setAttribute("cart", cart);
		session.setAttribute("totalQuantityCart", totalQuantity(cart));
		session.setAttribute("totalPriceCart", totalPrice(cart));
		return "redirect:" + request.getHeader("Referer").replaceAll("http://localhost:9999/Project-200926/", "/"); // request.getHeader("Referer") lấy back link
	}
	
	@RequestMapping(value="delete-cart/{id}")
	public String deleteCart(HttpSession session, HttpServletRequest request, @PathVariable("id") Integer id) {
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<Integer, Cart>();
		}
		cart = deleteCart(id, cart);
		session.setAttribute("cart", cart);
		session.setAttribute("totalQuantityCart", totalQuantity(cart));
		session.setAttribute("totalPriceCart", totalPrice(cart));
		return "redirect:" + request.getHeader("Referer").replaceAll("http://localhost:9999/Project-200926/", "/"); // request.getHeader("Referer") lấy back link
	}
	
	
	// các hàm xử lý
	public HashMap<Integer, Cart> addCart(int id, HashMap<Integer, Cart> cart) {
		Cart itemCart = new Cart();
		Session session = factory.getCurrentSession();
		String hql = "FROM Product WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		if(product != null && cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuantity(itemCart.getQuantity() + 1);
			itemCart.setTotalPrice(itemCart.getQuantity() * itemCart.getProduct().getPrice());
		}
		else {
			itemCart.setProduct(product);
			itemCart.setQuantity(1);
			itemCart.setTotalPrice(product.getPrice());
		}
		cart.put(id, itemCart);
		return cart;
	}
	
	public HashMap<Integer, Cart> addCartwithQuantity(int id, int quantity, HashMap<Integer, Cart> cart) {
		Cart itemCart = new Cart();
		Session session = factory.getCurrentSession();
		String hql = "FROM Product WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		if(product != null && cart.containsKey(id)) {
			itemCart = cart.get(id);
			itemCart.setQuantity(itemCart.getQuantity() + quantity);
			itemCart.setTotalPrice(itemCart.getQuantity() * itemCart.getProduct().getPrice());
		}
		else {
			itemCart.setProduct(product);
			itemCart.setQuantity(quantity);
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
