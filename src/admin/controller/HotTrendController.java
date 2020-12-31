package admin.controller;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.HotTrend;
import shop.entity.Product;

@Controller
@Transactional
@RequestMapping("/admin/hot-trend/")
public class HotTrendController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("list")
	public String hotTrendList(ModelMap model) {
		model.addAttribute("lstHot", getProHotTrend());
		return "admin/hot-trend";
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("lstProd", getPro());
		return "admin/add-hot-trend";
	}
	
	@RequestMapping(value="add/{id}", method=RequestMethod.GET)
	public String add(@PathVariable("id") int id) {
		Product p = getPro(id);
		HotTrend ht = new HotTrend();
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			ht.setProduct(p);
			ht.setCreated(new Date());
			ss.save(ht);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/admin/hot-trend/list.htm";
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {
		HotTrend ht = getProHotTrend(id);
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			ss.delete(ht);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/admin/hot-trend/list.htm";
	}
	
	public List<Product> getPro(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = ss.createQuery(hql);
		List<Product> array = query.list();
		return array;
	}
	
	public Product getPro(int id){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product WHERE Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		
		Product p = (Product) query.uniqueResult();
		return p;
	}
	
	public List<HotTrend> getProHotTrend(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM HotTrend";
		Query query = ss.createQuery(hql);
		List<HotTrend> list = query.list();
		return list;
	}
	
	public HotTrend getProHotTrend(int id){
		Session ss = factory.getCurrentSession();
		String hql = "FROM HotTrend WHERE Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		HotTrend ht = (HotTrend) query.uniqueResult();
		return ht;
	}
}
