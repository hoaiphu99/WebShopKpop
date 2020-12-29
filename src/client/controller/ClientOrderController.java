package client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import shop.entity.Order;
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
		String hql = "FROM Order o WHERE o.user.Id = :id ORDER BY o.Created DESC";
		Query query = ss.createQuery(hql);
		query.setParameter("id", u.getId());
		List<Order> lstOrder = query.list();
		model.addAttribute("lstOrder", lstOrder);
		
		return "client/orders";
	}
}
