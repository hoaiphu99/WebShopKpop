package admin.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
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

import shop.entity.BannerDiscount;
import shop.entity.Product;
import shop.entity.SliderDiscount;

@Controller
@Transactional
@RequestMapping("/admin/banner/")
public class BannerDiscountController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	BannerDiscount b;
	
	@RequestMapping("list")
	public String banner(ModelMap model) {
		model.addAttribute("banner", getBanner());
		return "admin/banner";
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("banner", new BannerDiscount());
		return "admin/add-banner";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("banner") BannerDiscount banner, @RequestParam("attachment") MultipartFile photo ) {
		
		if(photo.isEmpty()) {
			model.addAttribute("msgEmpty", "Vui lòng chọn file!");
			return "admin/add-banner";
		}
		try {
			String path = context.getRealPath("/resources/client/img/banner/" + photo.getOriginalFilename());
			photo.transferTo(new File(path));
		} catch (Exception e) {
			model.addAttribute("failFile", "Lỗi upload file ảnh!");
		}
		banner.setImage(photo.getOriginalFilename());
		if(!create(banner)) {
			model.addAttribute("msg", "Tạo thất bại. Tiêu đề dưới không được trùng!");
			model.addAttribute("banner", new BannerDiscount());
			return "admin/add-banner";
		}
		
		model.addAttribute("msg", "Tạo thành công.");
		
		return "admin/add-banner";
	}
	
	@RequestMapping(value="update/{id}" , method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") Integer id) {
		b = getBanner(id);
		model.addAttribute("banner", getBanner(id));
		return "admin/update-banner";
	}
	
	@RequestMapping(value="update" , method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("banner") BannerDiscount banner, @RequestParam("attachment") MultipartFile photo, BindingResult errors) {
		
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			if(photo.isEmpty()) {
				banner.setImage(b.getImage());
			}
			else {
				try {
					String path = context.getRealPath("/resources/client/img/banner/" + photo.getOriginalFilename());
					photo.transferTo(new File(path));
					banner.setImage(photo.getOriginalFilename());
				} catch (Exception e) {
					model.addAttribute("failFile", "Lỗi upload file ảnh!");
				}
			}
			banner.setCreated(b.getCreated());
			ss.update(banner);
			t.commit();
			model.addAttribute("msg", "Cập nhật thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "Cập nhật thất bại!");
		}
		b = null;
		return "redirect:/admin/banner/list.htm";
	}
	
	@RequestMapping(value="delete/{id}" , method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("banner", getBanner(id));
		return "admin/delete-banner";
	}
	
	@RequestMapping(value="delete" , method = RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("banner") BannerDiscount banner, @RequestParam("attachment") MultipartFile photo, BindingResult errors) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			ss.delete(banner);
			t.commit();
			model.addAttribute("msg", "Xóa thành công!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "Xóa thất bại!");
		}
		return "redirect:/admin/banner/list.htm";
	}
	
	// các hàm xử lý
		public boolean create(BannerDiscount banner) {
			Session ss = factory.openSession();
			Transaction t = ss.beginTransaction();
			
			try {
				banner.setCreated(new Date());
				ss.save(banner);
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
	
	public List<BannerDiscount> getBanner() {
		Session ss = factory.getCurrentSession();
		String hql = "FROM BannerDiscount";
		Query query = ss.createQuery(hql);
		List<BannerDiscount> b = query.list();
		return b;
	}
	
	public BannerDiscount getBanner(int id) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM BannerDiscount WHERE Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		BannerDiscount b = (BannerDiscount) query.uniqueResult();
		return b;
	}
}
