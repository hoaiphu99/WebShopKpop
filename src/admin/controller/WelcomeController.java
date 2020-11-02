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
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("user", new User());
		return "admin/login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(ModelMap model, HttpSession session ,@ModelAttribute("user") User user, BindingResult errors) {
		if(user.getUsername().trim().length() == 0)
			errors.rejectValue("username", "user", "Tên đăng nhập không được bỏ trống!");
		if(user.getPassword().trim().length() == 0)
			errors.rejectValue("password", "user", "Mật khẩu không được bỏ trống!");
		if(errors.hasErrors())
			return "admin/login";
		
		Session ss = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = ss.createQuery(hql);
		List<User> lstAcc = query.list();
		
		for (User i : lstAcc) {
			if(user.getUsername().equals(i.getUsername()) && user.getPassword().equals(i.getPassword())) {
				this.mUser = i;
				if(this.mUser.getUserRole().equals("admin")) {
					session.setAttribute("mUser", this.mUser);
					return "redirect:/admin/index.htm";
				}
				else {
					session.setAttribute("mUser", this.mUser);
					return "redirect:/index.htm";
				}
				
			}
		}
		model.addAttribute("msg", "Sai thông tin đăng nhập");
		return "admin/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
//		User u = new User();
//		u = (User) session.getAttribute("mUser");
		session.removeAttribute("mUser");
		return "redirect:/index.htm";
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
