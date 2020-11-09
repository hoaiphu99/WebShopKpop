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
		model.addAttribute("lstUserRole", listUserRole());
		return "admin/add-user";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("user") User user, @RequestParam("confirm_password") String confirm_password) {
		if(!user.getPassword().equals(confirm_password)) {
			model.addAttribute("failAdd", "Mật khẩu không trùng khớp!");
			model.addAttribute("user", new User());
			model.addAttribute("lstUserRole", listUserRole());
			return "admin/add-user";
		}
		if(!create(user)) {
			model.addAttribute("failAdd", "Tạo thất bại!");
			model.addAttribute("user", new User());
			model.addAttribute("lstUserRole", listUserRole());
			return "admin/add-user";
		}
		
		model.addAttribute("successAdd", "Tạo thành công.");
		model.addAttribute("user", new User());
		model.addAttribute("lstUserRole", listUserRole());
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
		model.addAttribute("user", user);
		model.addAttribute("lstUserRole", listUserRole());
		return "admin/update-user";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("user") User user, @RequestParam("confirm_password") String confirm_password) {
		if(!user.getPassword().equals(confirm_password)) {
			model.addAttribute("failAdd", "Mật khẩu không trùng khớp!");
			model.addAttribute("user", new User());
			model.addAttribute("lstUserRole", listUserRole());
			return "redirect:/admin/user/update/"+ user.getId() + ".htm";
		}
		
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			user.setCreated(new Date());
			ss.update(user);
			t.commit();
			model.addAttribute("msg", "Sửa thành công!");
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "Sửa thất bại!");
		}
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
			model.addAttribute("msg", "Xóa thành công!");
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "Xóa thất bại!");
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
	
	@ModelAttribute("lstUserRole")
	public List<String> listUserRole(){
		List<String> array = new ArrayList<>();
		array.add("admin");
		array.add("user");
		return array;
	}
}
