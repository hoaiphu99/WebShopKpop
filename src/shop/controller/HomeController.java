package shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import admin.controller.UserController;
import client.controller.ProfileController;
import shop.bean.Cart;
import shop.bean.EmailAccount;
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
	@Autowired
	JavaMailSender mailer;
	@Autowired
	EmailAccount account;
	
	@RequestMapping("index")
	public String homePage(HttpSession session) {
		session.setAttribute("menu", menu());
		return "client/index";
	}
	
	@RequestMapping("about")
	public String about() {
		return "client/about";
	}
	
	@RequestMapping("contact")
	public String contact() {
		return "client/contact";
	}
	
	@RequestMapping("faq")
	public String faq() {
		return "client/faq";
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
		
		User u = findUser(user.getUsername());
		if(u!= null) {
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				this.mUser = u;
				session.setAttribute("mUser", this.mUser);
				session.setAttribute("cart", this.cart);
				session.setAttribute("totalQuantityCart", 0);
				session.setAttribute("totalPriceCart", 0);
				
				if(this.mUser.getUserRole().equals("admin")) {
					return "redirect:/admin/index.htm";
				}
				else {
					return "redirect:/index.htm";
				} 
				
			}
		}
		model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
				+ "					  Tên đăng nhập hoặc mật khẩu không đúng!\r\n"
				+ "					</div>");
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
	public String register(ModelMap model, @ModelAttribute("user") User user, @RequestParam("confirm_password") String confirm_password, BindingResult errors) {
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
			return "client/register";
		}
			
		// kiểm tra username trùng
		User u = findUser(user.getUsername());
		if(u != null) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Tên đăng nhập đã bị trùng!\r\n"
					+ "					</div>");
			return "client/register";
		}
		
		User u1 = findUserByEmail(user.getEmail());
		// kiểm tra email trùng
		if(u1 != null && user.getEmail().equals(u1.getEmail())) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Email đã bị trùng!\r\n"
					+ "					</div>");
			return "admin/add-user";
		}
		
		if(!user.getPassword().equals(confirm_password)) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Mật khẩu không trùng khớp!\r\n"
					+ "					</div>");
			return "client/register";
		}
		if(!create(user)) {
			model.addAttribute("msg", "Tạo thất bại!");
			return "client/register";
		}
		
		model.addAttribute("msg", "Tạo thành công. Hãy đăng nhập vào trang web");
		return "client/register";
	}
	
	@RequestMapping(value="category/{category}", method=RequestMethod.GET)
	public String showCate(ModelMap model, @PathVariable("category") String category) {
		Session ss = factory.getCurrentSession();
		
		String hql = "SELECT COUNT(*) FROM Product WHERE category.Name = '" + category + "'";
		Query query = ss.createQuery(hql);
		long totalPro = (long) query.uniqueResult();
		int totalPage = (int) (totalPro / 8 + ((totalPro % 8 == 0) ? 0 : 1));
		
		hql = "FROM Product p WHERE p.category.Name = '" + category + "' ORDER BY p.Id DESC";
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
		
		hql = "FROM Product p WHERE p.category.Name = '" + category + "' ORDER BY p.Id DESC";
		query = ss.createQuery(hql);
		query.setFirstResult((stt - 1) * 8);
		query.setMaxResults(8);
		List<Product> arrays = query.list();
		
		model.addAttribute("lstProCate", arrays);
		model.addAttribute("cateName", category);
		model.addAttribute("totalPage", totalPage);
		
		return "client/category";
	}
	
	@RequestMapping(value="{category}/{id}", method=RequestMethod.GET)
	public String productDetails(ModelMap model, @PathVariable("category") String category, 
									@PathVariable("id") Integer id) {
		
		Product product = getProduct(id);
		model.addAttribute("product", product);
		model.addAttribute("relatedProd", relatedProduct(category));
		return "client/product-details";
	}
	
	
	// các model attribute
	
	@ModelAttribute("lstPro")
	public List<Product> getPro(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p ORDER BY p.Id DESC";
		Query query = ss.createQuery(hql);
		query.setMaxResults(8);
		List<Product> array = query.list();
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
	
	public List<Product> relatedProduct(String cateName){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product WHERE category.Name = :cateName ORDER BY Id DESC";
		Query query = ss.createQuery(hql);
		query.setParameter("cateName", cateName);
		query.setFirstResult(0);
		query.setMaxResults(4);
		List<Product> lst = query.list();
		return lst;
	}
	
	public Product getProduct(int id){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product WHERE Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		Product p = (Product) query.uniqueResult();
		return p;
	}
	
	@ModelAttribute("lstHot")
	public List<HotTrend> getProHotTrend(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM HotTrend ORDER BY Id DESC";
		Query query = ss.createQuery(hql);
		query.setMaxResults(3);
		List<HotTrend> list = query.list();
		return list;
	}
	
	@ModelAttribute("lstSlider")
	public List<SliderDiscount> getSlider(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM SliderDiscount ORDER BY Id DESC";
		Query query = ss.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(3);
		List<SliderDiscount> list = query.list();
		
		return list;
	}
	
	@ModelAttribute("lstBanner")
	public List<BannerDiscount> getBanner(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM BannerDiscount ORDER BY Id DESC";
		Query query = ss.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<BannerDiscount> list = query.list();
		if(list.isEmpty()) {
			list.add(new BannerDiscount());
			list.get(0).setBigTitle("Tạm thời không có khuyến mãi");
			list.get(0).setSmallTitle(" ");
			list.get(0).setDiscount(0);
			list.get(0).setDay(00);
			list.get(0).setMonth(00);
			list.get(0).setYear(00);
		}
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
}
