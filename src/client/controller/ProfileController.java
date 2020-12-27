package client.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RequestParam;

import shop.bean.EmailAccount;
import shop.entity.User;

@Controller
@Transactional
public class ProfileController {

	@Autowired
	SessionFactory factory;
	@Autowired
	JavaMailSender mailer;
	@Autowired
	EmailAccount account;
	
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
	public String editProfile(HttpSession ss, ModelMap model, @ModelAttribute("user") User user) {
		User u = (User) ss.getAttribute("mUser");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			user.setCreated(u.getCreated());
			user.setUserRole(u.getUserRole());
			session.update(user);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Lưu thành công\r\n"
					+ "					</div>");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Lưu thất bại\r\n"
					+ "					</div>");
		}
		ss.removeAttribute("mUser");
		ss.setAttribute("mUser", user);
		return "client/profile-edit";
	}
	
	@RequestMapping(value = "doi-mat-khau", method = RequestMethod.GET)
	public String changePassword(HttpSession session, HttpServletRequest request, ModelMap model) {
		session = request.getSession();
		User u = new User();
		u = (User) session.getAttribute("mUser");
		model.addAttribute("user", u);
		return "client/change-password";
	}
	
	@RequestMapping(value = "doi-mat-khau", method = RequestMethod.POST)
	public String changePassword(HttpSession ss, HttpServletRequest request, ModelMap model, @ModelAttribute("user") User user) {
		ss = request.getSession();
		User u = new User();
		u = (User) ss.getAttribute("mUser");
		
		user.setId(u.getId());
		user.setUsername(u.getUsername());
		user.setCreated(u.getCreated());
		user.setUserRole(u.getUserRole());
		user.setAddress(u.getAddress());
		user.setEmail(u.getEmail());
		user.setGender(u.getGender());
		user.setName(u.getName());
		user.setPhone(u.getPhone());
		
		String oldPass = request.getParameter("old_password");
		String cfmPass = request.getParameter("confirm_password");
		if(!oldPass.equals(u.getPassword())) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Mật khẩu cũ không đúng\r\n"
					+ "					</div>");
			return "client/change-password";
		}
		if(!cfmPass.equals(user.getPassword())) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Nhập lại mật khẩu không khớp\r\n"
					+ "					</div>");
			return "client/change-password";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			
			session.update(user);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Đổi mật khẩu thành công\r\n"
					+ "					</div>");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Đổi mật khẩu thất bại\r\n"
					+ "					</div>");
		}
		ss.removeAttribute("mUser");
		ss.setAttribute("mUser", user);
		return "client/change-password";
	}
	
	@RequestMapping(value="quen-mat-khau", method = RequestMethod.GET)
	public String forgotPassword() {
		return "client/forgot-password";
	}
	
	@RequestMapping(value="quen-mat-khau", method = RequestMethod.POST)
	public String forgotPassword(ModelMap model, @RequestParam("username") String username, @RequestParam("email")String email) {
		
		User u = findUser(username);
		String newPW = createNewPW(8);
		if(!email.equals(u.getEmail())) {
			return "client/forgot-password";
		}
		try {
			// Tạo mail
			MimeMessage mail = mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(account.getUsername(), account.getUsername());
			helper.setTo(u.getEmail());
			helper.setReplyTo(account.getUsername(), account.getUsername());
			helper.setSubject("Khôi phục mật khẩu");
			helper.setText("Mật khẩu mới là: " + newPW, true);
			setNewPW(u, newPW);
			// Gửi mail
			mailer.send(mail);
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Gửi thành công\r\n"
					+ "					</div>");
		}
		catch(Exception ex) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Gửi thất bại\r\n"
					+ "					</div>");
			return "client/forgot-password";
		}
		return "client/forgot-password";
	}
	
	public User findUser(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.Username = '" + username + "'";
		Query query = session.createQuery(hql);
		User u = (User)query.uniqueResult();
		
		return u;
	}
	
	public String createNewPW(int len) {
//		int leftLimit = 48; // numeral '0'
//	    int rightLimit = 122; // letter 'z'
//	    int targetStringLength = 10;
//	    Random random = new Random();
//	    
//	    String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	    
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
	    for(int i = 0; i < len; i++)
	    	sb.append(AB.charAt(rnd.nextInt(AB.length())));
		
	    return sb.toString();
	}
	
	public void setNewPW(User u, String newPW) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			u.setPassword(newPW);
			ss.save(u);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
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
