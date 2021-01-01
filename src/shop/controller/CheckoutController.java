package shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.bean.Cart;
import shop.bean.EmailAccount;
import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.Status;
import shop.entity.User;

@Controller
@Transactional
public class CheckoutController {
	HashMap<Integer, Cart> cart = null;
	@Autowired
	SessionFactory factory;

	@RequestMapping(value="xac-nhan-mua-hang", method = RequestMethod.GET)
	public String blillingDetail(HttpSession session, HttpServletRequest request, ModelMap model) {
		session = request.getSession();
		User u = new User();
		u = (User) session.getAttribute("mUser");
		model.addAttribute("user", u);
		
		return "client/checkout";
	}
	
	@RequestMapping(value="xac-nhan-mua-hang", method = RequestMethod.POST)
	public String checkout(HttpSession ss, HttpServletRequest request, ModelMap model, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		ss = request.getSession();
		User u = new User();
		u = (User) ss.getAttribute("mUser");
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)ss.getAttribute("cart");
		double totalPriceCart = (double) ss.getAttribute("totalPriceCart");
		int totalQuantityCart = (int) ss.getAttribute("totalQuantityCart");
		
		Order order = new Order();
		
		Status stt = getStatus(1);
		Order o = null;
		try {
			order.setUser(u);
			order.setCreated(new Date());
			order.setStatus(stt);
			order.setTotalPrice(totalPriceCart);
			order.setTotalQuantity(totalQuantityCart);
			session.save(order);
			t.commit();
			o = getOder(order.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		}
		finally {
			//session.close();
		}
		if(o != null) {
			for(Map.Entry<Integer, Cart> itemCart : cart.entrySet()) {
				t = session.beginTransaction();
				OrderDetail orderDetail = new OrderDetail();
				try {
					orderDetail.setOrder(o);
					orderDetail.setProduct(itemCart.getValue().getProduct());
					orderDetail.setQuantity(itemCart.getValue().getQuantity());
					orderDetail.setUnitPrice(itemCart.getValue().getTotalPrice());
					session.save(orderDetail);
					t.commit();
				} catch (Exception e) {
					t.rollback();
				}
				
			}
		}
		
		ss.setAttribute("cart", this.cart);
		ss.setAttribute("totalQuantityCart", 0);
		ss.setAttribute("totalPriceCart", 0);
		model.addAttribute("msg", "Đặt hàng thành công! Đang chờ xác nhận.");
		return "client/success-checkout";
	}
	
	public Status getStatus(int stt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Status WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", stt);
		Status status = (Status) query.uniqueResult();
		return status;
	}
	
	public Order getOder(int stt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", stt);
		Order oder = (Order) query.uniqueResult();
		return oder;
	}
}
