package admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.User;

@Controller
@Transactional
@RequestMapping("/admin/")
public class WelcomeController {
	User mUser = new User();
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String manage(ModelMap model) {
		model.addAttribute("totalPro", totalItem());
		return "admin/index";
	}
	
	public int totalItem() {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT COUNT(p) FROM Product p";
		Query query = ss.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	@RequestMapping("add-product")
	public String addProduct() {
		return "admin/add-product";
	}

}
