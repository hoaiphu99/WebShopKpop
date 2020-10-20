package shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.entity.Product;

@Transactional
@Controller
public class HomeController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String homePage() {
		return "client/index";
	}
	
	@RequestMapping(value="category/{category}", method=RequestMethod.GET)
	public String showCate(ModelMap model, @PathVariable("category") String category) {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT p.Name, p.Price, p. Discount, p.Quantity, p.Photo, p.category.Name, p.Id FROM Product p "
					+ "WHERE p.category.Name = '" + category + "'";
		Query query = ss.createQuery(hql);
		List<Object> arrays = query.list();
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
	public List<Object> getPro(){
		Session ss = factory.getCurrentSession();
		String hql = "SELECT p.Name, p.Price, p. Discount, p.Quantity, p.Photo, p.category.Name, p.Id FROM Product p";
		Query query = ss.createQuery(hql);
		query.setMaxResults(8);
		List<Object> array = query.list();
		return array;
	}
}
