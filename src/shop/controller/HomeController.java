package shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import admin.controller.UserController;
import shop.bean.Cart;
import shop.entity.Product;
import shop.entity.User;
import shop.function.CartFunc;

@Transactional
@Controller
public class HomeController {
	User mUser = new User();
	HashMap<Integer, Cart> cart = new HashMap<Integer, Cart>();
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String homePage() {
		return "client/index";
	}
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("user", new User());
		return "client/login";
	}
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(ModelMap model, HttpSession session, @ModelAttribute("user") User user, BindingResult errors) {
		if(user.getUsername().trim().length() == 0)
			errors.rejectValue("username", "user", "Tên đăng nhập không được bỏ trống!");
		if(user.getPassword().trim().length() == 0)
			errors.rejectValue("password", "user", "Mật khẩu không được bỏ trống!");
		if(errors.hasErrors())
			return "client/login";
		
		Session ss = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = ss.createQuery(hql);
		List<User> lstAcc = query.list();
		
		for (User i : lstAcc) {
			if(user.getUsername().equals(i.getUsername()) && user.getPassword().equals(i.getPassword())) {
				this.mUser = i;
				if(this.mUser.getUserRole().equals("admin")) {
					session.setAttribute("mUser", this.mUser);
					session.setAttribute("cart", this.cart);
					session.setAttribute("totalQuantityCart", 0);
					session.setAttribute("totalPriceCart", 0);
					return "redirect:/admin/index.htm";
				}
				else {
					session.setAttribute("mUser", this.mUser);
					session.setAttribute("cart", this.cart);
					session.setAttribute("totalQuantityCart", 0);
					session.setAttribute("totalPriceCart", 0);
					return "redirect:/index.htm";
				} 
				
			}
		}
		model.addAttribute("msg", "Sai thông tin đăng nhập");
		return "client/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
//		User u = new User();
//		u = (User) session.getAttribute("mUser");
		session.removeAttribute("mUser");
		return "redirect:/index.htm";
	}
	
	@RequestMapping(value="signin", method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("user", new User());
		return "client/register";
	}
	
	@RequestMapping(value="signin", method = RequestMethod.POST)
	public String register(ModelMap model, @ModelAttribute("user") User user, @RequestParam("confirm_password") String confirm_password) {
		if(!user.getPassword().equals(confirm_password)) {
			model.addAttribute("failAdd", "Mật khẩu không trùng khớp!");
			model.addAttribute("user", new User());
			model.addAttribute("lstUserRole", listUserRole());
			return "client/register";
		}
		if(!create(user)) {
			model.addAttribute("failAdd", "Tạo thất bại!");
			model.addAttribute("user", new User());
			model.addAttribute("lstUserRole", listUserRole());
			return "client/register";
		}
		
		model.addAttribute("successAdd", "Tạo thành công.");
		model.addAttribute("user", new User());
		model.addAttribute("lstUserRole", listUserRole());
		return "client/register";
	}
	
	@RequestMapping(value="category/{category}", method=RequestMethod.GET)
	public String showCate(ModelMap model, @PathVariable("category") String category) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.category.Name = '" + category + "'";
		Query query = ss.createQuery(hql);
		List<Product> arrays = query.list();
		model.addAttribute("lstProCate", arrays);
		model.addAttribute("cateName", category);
		return "client/category";
	}
	
	@RequestMapping(value="{category}/{proName}", method=RequestMethod.GET)
	public String productDetails(ModelMap model, @PathVariable("category") String category, 
									@PathVariable("proName") Integer proName) {
		Session ss = factory.getCurrentSession();
		Product product = (Product) ss.get(Product.class, proName);
		model.addAttribute("product", product);
		return "client/product-details";
	}
	
	
	// các model attribute
	@ModelAttribute("menu")
	public List<String> menu(){
		List<String> menu = new ArrayList<String>();
		menu.add("Album");
		menu.add("Magazine");
		menu.add("Photobook");
		menu.add("Beauty");
		menu.add("Fashion");
		return menu;
	}
	
	@ModelAttribute("lstPro")
	public List<Product> getPro(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p";
		Query query = ss.createQuery(hql);
		query.setMaxResults(8);
		List<Product> array = query.list();
		return array;
	}
	
	@ModelAttribute("lstUserRole")
	public List<String> listUserRole(){
		List<String> array = new ArrayList<>();
		array.add("admin");
		array.add("user");
		return array;
	}
	
	@ModelAttribute("gender")
	public String[] getGender() {
		String[] gender = {
				"true",
				"false"
		};
		return gender;
	}
	
	// các hàm xử lý
	public boolean create(User user) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		try {
			user.setCreated(new Date());
			user.setUserRole("user");
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
	
}
