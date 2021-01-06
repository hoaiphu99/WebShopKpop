package client.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.entity.Order;
import shop.entity.OrderDetail;
import shop.entity.Status;
import shop.entity.User;

@Controller
@Transactional
public class ClientOrderController {
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("xem-don-hang")
	public String orders(HttpSession session, ModelMap model) {
		User u = (User) session.getAttribute("mUser");
		Session ss = factory.getCurrentSession();
		String hql = "FROM Order o WHERE o.user.Id = :id ORDER BY o.Id DESC";
		Query query = ss.createQuery(hql);
		query.setParameter("id", u.getId());
		List<Order> lstOrder = query.list();
		model.addAttribute("lstOrder", lstOrder);
		
		return "client/orders";
	}
	
	@RequestMapping(value="huy-don-hang/{id}")
	public String cancel(@PathVariable("id") Integer id) {
		Order order = getOrder(id);
		Status stt = getStatus(3);
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		Collection<OrderDetail> orderDetail = order.getOrderdetails();
		ArrayList<OrderDetail> d = new ArrayList<OrderDetail>(orderDetail);
		int quantity = 0;
		try {
			order.setStatus(stt);
			ss.update(order);
			t.commit();
			// cap nhat lai so luong san pham
			for (OrderDetail i : d) {
				quantity = i.getProduct().getQuantity() + i.getQuantity();
				updateQuantityProd(i.getProduct().getId(), quantity);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/xem-don-hang.htm";
	}
	
	@RequestMapping(value="da-nhan/{id}")
	public String delivered(@PathVariable("id") Integer id) {
		Order order = getOrder(id);
		Status stt = getStatus(4);
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		try {
			order.setStatus(stt);
			ss.update(order);
			t.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/xem-don-hang.htm";
	}
	
	public Order getOrder(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Order order = (Order) query.uniqueResult();
		session.clear();
		return order;
	}
	
	public Status getStatus(int stt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Status WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", stt);
		Status status = (Status) query.uniqueResult();
		session.clear();
		return status;
	}
	
	public void updateQuantityProd(int id, int quantity) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE Product SET Quantity = :quantity WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("quantity", quantity);
		query.setParameter("id", id);
		
		query.executeUpdate();
		
	}
}
