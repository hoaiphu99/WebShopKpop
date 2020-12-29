package admin.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.Order;
import shop.entity.Product;
import shop.entity.Status;

@Controller
@Transactional
@RequestMapping("/admin/order/")
public class OrderController {
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("tat-ca-don-hang")
	public String list(ModelMap model) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM Order";
		Query query = ss.createQuery(hql);
		List<Order> list = query.list();
		model.addAttribute("lstOrder", list);
		return "admin/list-order";
	}
	
	@RequestMapping(value="accept/{id}")
	public String accept(@PathVariable("id") Integer id) {
		Order order = getOrder(id);
		Status stt = getStatus(2);
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
		return "redirect:/admin/order/tat-ca-don-hang.htm";
	}
	
	@RequestMapping(value="cancel/{id}")
	public String cancel(@PathVariable("id") Integer id) {
		Order order = getOrder(id);
		Status stt = getStatus(3);
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
		return "redirect:/admin/order/tat-ca-don-hang.htm";
	}
	
	/* các hàm xử lý */
	
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
}
