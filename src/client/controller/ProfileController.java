package client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.User;

@Controller
@Transactional
public class ProfileController {

	@Autowired
	SessionFactory factory;
	
	@RequestMapping("thong-tin-tai-khoan")
	public String profile() {
		return "client/profile";
	}
	
	@RequestMapping(value="chinh-sua-thong-tin", method = RequestMethod.GET)
	public String editProfile(HttpSession session, HttpServletRequest request, ModelMap model) {
		session = request.getSession();
		User u = new User();
		u = (User) session.getAttribute("mUser");
		model.addAttribute("user", u);
		return "client/profile-edit";
	}
	
	@RequestMapping(value="chinh-sua-thong-tin", method = RequestMethod.POST)
	public String editProfile(ModelMap model, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			user.setCreated(new Date());
			user.setUserRole("user");
			session.update(user);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Lưu thành công\r\n"
					+ "					</div>");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Lưu thất bại\r\n"
					+ "					</div>");
		}
		return "client/profile-edit";
	}
	
	@ModelAttribute("gender")
	public String[] getGender() {
		String[] gender = {
				"true",
				"false"
		};
		return gender;
	}
	
	@ModelAttribute("menu")
	public List<String> menu(ModelMap model){
		List<String> menu = new ArrayList<String>();
		menu.add("Album");
		menu.add("Magazine");
		menu.add("Photobook");
		menu.add("Beauty");
		menu.add("Fashion");
		model.addAttribute("menu", menu);
		return menu;
	}
}
