package admin.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shop.entity.Product;
import shop.entity.User;

@Controller
@Transactional
@RequestMapping("/admin/user/")
public class UserController {
	
	@Autowired
	SessionFactory factory;
	User u;
	
	@RequestMapping("list")
	public String list(ModelMap model) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM User u";
		Query query = ss.createQuery(hql);
		List<Product> list = query.list();
		model.addAttribute("lstUser", list);
		return "admin/list-user";
	}
	
	// thêm user
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("user", new User());
		return "admin/add-user";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("user") User user, @RequestParam("confirm_password") String confirm_password, BindingResult errors) {
		if(user.getName().trim().length() == 0)
			errors.rejectValue("name", "user", "Họ tên không được bỏ trống!");
		if(user.getUsername().trim().length() == 0)
			errors.rejectValue("username", "user", "Tên đăng nhập không được bỏ trống!");
		if(user.getPassword().trim().length() == 0)
			errors.rejectValue("password", "user", "Mật khẩu không được bỏ trống!");
		if(user.getAddress().trim().length() == 0)
			errors.rejectValue("address", "user", "Địa chỉ không được bỏ trống!");
		if(user.getPhone().trim().length() == 0)
			errors.rejectValue("phone", "user", "Số điện thoại không được bỏ trống!");
		if(user.getEmail().trim().length() == 0)
			errors.rejectValue("email", "user", "Email không được bỏ trống!");
		if(errors.hasErrors()) {
			return "admin/add-user";
		}
			
		User u = findUser(user.getUsername());
		User u1 = findUserByEmail(user.getEmail());
		// kiểm tra email trùng
		if(u1 != null && user.getEmail().equals(u1.getEmail())) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Email đã bị trùng!\r\n"
					+ "					</div>");
			return "admin/add-user";
		}
		
		// kiểm tra username trùng
		if(u != null) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Tên đăng nhập đã bị trùng!\r\n"
					+ "					</div>");
			return "admin/add-user";
		}
		
		if(!user.getPassword().equals(confirm_password)) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Mật khẩu không trùng khớp!\r\n"
					+ "					</div>");
			return "admin/add-user";
		}
		if(!create(user)) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Tạo thất bại!\r\n"
					+ "					</div>");
			return "admin/add-user";
		}
		
		model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "					  Tạo thành công!\r\n"
				+ "					</div>");
		return "admin/add-user";
	}
	
	// sửa user
	@RequestMapping(value="update/{id}", method=RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") Integer id) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		u = user;
		model.addAttribute("user", user);
		return "admin/update-user";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("user") User user, @RequestParam("confirm_password") String confirm_password, BindingResult errors) {
		if(user.getName().trim().length() == 0)
			errors.rejectValue("name", "user", "Họ tên không được bỏ trống!");
		if(user.getUsername().trim().length() == 0)
			errors.rejectValue("username", "user", "Tên đăng nhập không được bỏ trống!");
		if(user.getPassword().trim().length() == 0)
			errors.rejectValue("password", "user", "Mật khẩu không được bỏ trống!");
		if(user.getAddress().trim().length() == 0)
			errors.rejectValue("address", "user", "Địa chỉ không được bỏ trống!");
		if(user.getPhone().trim().length() == 0)
			errors.rejectValue("phone", "user", "Số điện thoại không được bỏ trống!");
		if(user.getEmail().trim().length() == 0)
			errors.rejectValue("email", "user", "Email không được bỏ trống!");
		if(errors.hasErrors()) {
			return "admin/update-user";
		}
		
		if(!user.getPassword().equals(confirm_password)) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Mật khẩu không trùng khớp!\r\n"
					+ "					</div>");
			return "admin/update-user";
		}
		
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			user.setCreated(u.getCreated());
			ss.update(user);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Cập nhật thành công!\r\n"
					+ "					</div>");
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Cập nhật thất bại!\r\n"
					+ "					</div>");
			return "admin/update-user";
		}
		u = null;
		return "redirect:/admin/user/list.htm";
	}
	
	// xóa user
	@RequestMapping(value="delete/{id}", method=RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") Integer id) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		model.addAttribute("user", user);
		model.addAttribute("lstUserRole", listUserRole());
		return "admin/delete-user";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("user") User user) {
		
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			ss.delete(user);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Xóa thành công!\r\n"
					+ "					</div>");
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Xóa thất bại!\r\n"
					+ "					</div>");
			return "admin/delete-user";
		}
		return "redirect:/admin/user/list.htm";
	}
	
	// các hàm xử lý
	public boolean create(User user) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		try {
			user.setCreated(new Date());
			ss.save(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return false;
		}
		finally {
			ss.close();
		}
		return true;
	}
	
	public User findUser(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.Username = '" + username + "'";
		Query query = session.createQuery(hql);
		User u = (User)query.uniqueResult();
		
		return u;
	}
	
	public User findUserByEmail(String email) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User Where Email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		User u = (User) query.uniqueResult();
		
		return u;
	}
	
	@ModelAttribute("lstUserRole")
	public List<String> listUserRole(){
		List<String> array = new ArrayList<>();
		array.add("admin");
		array.add("user");
		return array;
	}
}
