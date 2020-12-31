package admin.controller;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.entity.Feature;
import shop.entity.Product;

@Controller
@Transactional
@RequestMapping("/admin/feature/")
public class FeatureController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("feature", new Feature());
		return "admin/add-feature";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("feature") Feature feature) {
		deleteOldFeature();
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		try {
			ss.save(feature);
			t.commit();
			
			model.addAttribute("successAdd", "Thành công");
		} catch (Exception e) {
			model.addAttribute("failAdd", "Thất bại");
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "admin/add-feature";
	}
	
	public void deleteOldFeature() {
		Session ss = factory.getCurrentSession();
		String hql = "DELETE FROM Feature";
		Query query = ss.createQuery(hql);
		query.executeUpdate();
		ss.clear();
	}
}
