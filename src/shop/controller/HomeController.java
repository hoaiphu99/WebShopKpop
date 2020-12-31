package shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import shop.entity.BannerDiscount;
import shop.entity.Feature;
import shop.entity.HotTrend;
import shop.entity.Product;
import shop.entity.SliderDiscount;
import shop.entity.User;

@Transactional
@Controller
public class HomeController {
	User mUser = new User();
	HashMap<Integer, Cart> cart = new HashMap<Integer, Cart>();
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String homePage(HttpSession session) {
		session.setAttribute("menu", menu());
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
		this.cart.clear();
		session.removeAttribute("mUser");
		session.removeAttribute("cart");
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
			model.addAttribute("gender", getGender());
			return "client/register";
		}
		if(!create(user)) {
			model.addAttribute("failAdd", "Tạo thất bại!");
			model.addAttribute("user", new User());
			model.addAttribute("lstUserRole", listUserRole());
			model.addAttribute("gender", getGender());
			return "client/register";
		}
		
		model.addAttribute("successAdd", "Tạo thành công.");
		model.addAttribute("user", new User());
		model.addAttribute("lstUserRole", listUserRole());
		model.addAttribute("gender", getGender());
		return "client/register";
	}
	
	@RequestMapping(value="category/{category}", method=RequestMethod.GET)
	public String showCate(ModelMap model, @PathVariable("category") String category) {
		Session ss = factory.getCurrentSession();
		
		String hql = "SELECT COUNT(*) FROM Product WHERE category.Name = '" + category + "'";
		Query query = ss.createQuery(hql);
		long totalPro = (long) query.uniqueResult();
		int totalPage = (int) (totalPro / 8 + ((totalPro % 8 == 0) ? 0 : 1));
		
		hql = "FROM Product p WHERE p.category.Name = '" + category + "'";
		query = ss.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(8);
		List<Product> arrays = query.list();
		
		model.addAttribute("lstProCate", arrays);
		model.addAttribute("cateName", category);
		model.addAttribute("totalPage", totalPage);
		return "client/category";
	}
	
	@RequestMapping(value="category/{category}/page{stt}", method=RequestMethod.GET)
	public String showCatePage(ModelMap model, @PathVariable("category") String category, @PathVariable("stt") int stt) {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Product WHERE category.Name = '" + category + "'";
		Query query = ss.createQuery(hql);
		long totalPro = (long) query.uniqueResult();
		int totalPage = (int) (totalPro / 8 + ((totalPro % 8 == 0) ? 0 : 1));
		
		if(stt > totalPage)
			return "redirect:/category/" + category +".htm";
		
		hql = "FROM Product p WHERE p.category.Name = '" + category + "'";
		query = ss.createQuery(hql);
		query.setFirstResult((stt - 1) * 8);
		query.setMaxResults(8);
		List<Product> arrays = query.list();
		
		model.addAttribute("lstProCate", arrays);
		model.addAttribute("cateName", category);
		model.addAttribute("totalPage", totalPage);
		
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
	
	@ModelAttribute("lstPro")
	public List<Product> getPro(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p";
		Query query = ss.createQuery(hql);
		query.setMaxResults(8);
		List<Product> array = query.list();
		return array;
	}
	
	public List<String> listUserRole(){
		List<String> array = new ArrayList<>();
		array.add("admin");
		array.add("user");
		return array;
	}
	
	public List<String> getGender() {
		List<String> array = new ArrayList<>();
		array.add("true");
		array.add("false");
		return array;
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
	
	public List<String> menu(){
		List<String> menu = new ArrayList<String>();
		menu.add("Album");
		menu.add("Magazine");
		menu.add("Photobook");
		menu.add("Beauty");
		menu.add("Fashion");
		return menu;
	}
	
	@ModelAttribute("lstHot")
	public List<HotTrend> getProHotTrend(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM HotTrend ORDER BY Created";
		Query query = ss.createQuery(hql);
		query.setMaxResults(3);
		List<HotTrend> list = query.list();
		return list;
	}
	
	@ModelAttribute("lstSlider")
	public List<SliderDiscount> getSlider(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM SliderDiscount ORDER BY Created";
		Query query = ss.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<SliderDiscount> list = query.list();
		
		return list;
	}
	
	@ModelAttribute("lstBanner")
	public List<BannerDiscount> getBanner(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM BannerDiscount ORDER BY Created";
		Query query = ss.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<BannerDiscount> list = query.list();
		
		return list;
	}
	
	@ModelAttribute("lstBestSaler") 
	public List<Object[]> getProdBestSaler(){ 
		Session ss = factory.getCurrentSession(); 
		String hql = "SELECT d.product.Id, d.product.Name, d.product.Photo, d.product.Price, d.product.Discount, d.product.category.Name, SUM(d.Quantity)"
				+ " FROM OrderDetail d WHERE d.order.status.Id != 1 AND d.order.status.Id != 3 GROUP BY d.product.Id, d.product.Name, d.product.Photo, d.product.Price, d.product.Discount, d.product.category.Id, d.product.category.Name ORDER BY SUM(d.Quantity) DESC"; 
	    Query query = ss.createQuery(hql);
	    query.setFirstResult(0);
	    query.setMaxResults(3);
	    List<Object[]> list = query.list(); 
	    return list; 
    }
	
	@ModelAttribute("lstFeature") 
	public List<Object[]> getProdFeature(){ 
		Session ss = factory.getCurrentSession();
		String hql = "SELECT DateStart, DateEnd FROM Feature";
		Query query = ss.createQuery(hql);
		query.setFirstResult(0);
	    query.setMaxResults(1);
	    List<Object[]> date = query.list();

		hql = "SELECT d.product.Id, d.product.Name, d.product.Photo, d.product.Price, d.product.Discount, d.product.category.Name, SUM(d.Quantity) "
				+ "FROM OrderDetail d WHERE d.order.Created BETWEEN :start AND :end AND d.order.status.Id != 1 AND d.order.status.Id != 3 "
				+ "GROUP BY d.product.Id, d.product.Name, d.product.Photo, d.product.Price, d.product.Discount, d.product.category.Name "
				+ "ORDER BY SUM(d.Quantity) DESC"; 
	    query = ss.createQuery(hql);
	    query.setParameter("start", date.get(0)[0]);
	    query.setParameter("end", date.get(0)[1]);
	    query.setFirstResult(0);
	    query.setMaxResults(3);
	    List<Object[]> list = query.list(); 
	    return list; 
    }
  
	 
	 
}
