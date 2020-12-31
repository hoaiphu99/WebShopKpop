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

import shop.entity.SliderDiscount;


@Controller
@Transactional
@RequestMapping("/admin/slider/")
public class SliderDiscountController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("list")
	public String slider(ModelMap model) {
		model.addAttribute("slider", getSlider());
		return "admin/slider";
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("slider", new SliderDiscount());
		return "admin/add-slider";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("slider") SliderDiscount slider) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			slider.setCreated(new Date());
			ss.save(slider);
			t.commit();
			
			model.addAttribute("successAdd", "Thành công");
		} catch (Exception e) {
			model.addAttribute("failAdd", "Thất bại");
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "admin/add-slider";
	}
	
	@RequestMapping(value="update/{id}", method=RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("slider", getSlider(id));
		return "admin/update-slider";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("slider") SliderDiscount slider) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			slider.setCreated(new Date());
			ss.update(slider);
			t.commit();
			
			model.addAttribute("successAdd", "Thành công");
		} catch (Exception e) {
			model.addAttribute("failAdd", "Thất bại");
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/admin/slider/list.htm";
	}
	
	@RequestMapping(value="delete/{id}", method=RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("slider", getSlider(id));
		return "admin/delete-slider";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("slider") SliderDiscount slider) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			ss.delete(slider);
			t.commit();
			
			model.addAttribute("successAdd", "Thành công");
		} catch (Exception e) {
			model.addAttribute("failAdd", "Thất bại");
			t.rollback();
		}
		finally {
			ss.close();
		}
		return "redirect:/admin/slider/list.htm";
	}
	
	public List<SliderDiscount> getSlider() {
		Session ss = factory.getCurrentSession();
		String hql = "FROM SliderDiscount";
		Query query = ss.createQuery(hql);
		List<SliderDiscount> s = query.list();
		return s;
	}
	
	public SliderDiscount getSlider(int id) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM SliderDiscount WHERE Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		SliderDiscount s = (SliderDiscount) query.uniqueResult();
		return s;
	}
}
