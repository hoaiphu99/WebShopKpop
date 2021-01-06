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

import shop.entity.Category;
import shop.entity.Product;

@Controller
@Transactional
@RequestMapping("/admin/product/")
public class ProductController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	Product p;
	
	// thêm sản phẩm
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("product", new Product());
		return "admin/add-product";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("product") Product product, @RequestParam("attachment") MultipartFile photo, @RequestParam("discount") float discount, BindingResult errors) {
		if(product.getName().trim().length() == 0)
			errors.rejectValue("name", "product", "Tên sản phẩm không được bỏ trống!");
		if(product.getPrice() == null)
			errors.rejectValue("price", "product", "Giá không được bỏ trống!");
		if(product.getPrice() != null && product.getPrice() < 0)
			errors.rejectValue("price", "product", "Giá không âm!");
		if(product.getQuantity() == null)
			errors.rejectValue("quantity", "product", "Số lượng không được bỏ trống!");
		if(product.getQuantity() != null && product.getQuantity() < 0)
			errors.rejectValue("quantity", "product", "Số lượng không âm!");
		if(errors.hasErrors()) {
			return "admin/add-product";
		}
			
		
		if(photo.isEmpty()) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Vui lòng chọn file ảnh!\r\n"
					+ "					</div>");
			return "admin/add-product";
		}
		
		try {
			String path = context.getRealPath("/resources/client/img/product/" + photo.getOriginalFilename());
			photo.transferTo(new File(path));
		} catch (Exception e) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Lỗi upload file ảnh!\r\n"
					+ "					</div>");
			return "admin/add-product";
		}
		product.setPhoto(photo.getOriginalFilename());
		product.setDiscount(discount / 100);
		if(!create(product)) {
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Tạo thất bại tên sản phẩm không được trùng!\r\n"
					+ "					</div>");
			return "admin/add-product";
		}
		
		model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "					  Tạo thành công!\r\n"
				+ "					</div>");
		
		return "admin/add-product";
	}
	
	// sửa sản phẩm
	@RequestMapping(value="update/{id}" , method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") Integer id) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		p = product;
		model.addAttribute("product", product);
		return "admin/update-product";
	}
	
	@RequestMapping(value="update" , method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("product") Product product, @RequestParam("attachment") MultipartFile photo, BindingResult errors) {
		if(product.getName().trim().length() == 0)
			errors.rejectValue("name", "product", "Tên sản phẩm không được bỏ trống!");
		if(product.getPrice() == null)
			errors.rejectValue("price", "product", "Giá không được bỏ trống!");
		if(product.getPrice() != null && product.getPrice() < 0)
			errors.rejectValue("price", "product", "Giá không âm!");
		if(product.getQuantity() == null)
			errors.rejectValue("quantity", "product", "Số lượng không được bỏ trống!");
		if(product.getQuantity() != null && product.getQuantity() < 0)
			errors.rejectValue("quantity", "product", "Số lượng không âm!");
		if(errors.hasErrors()) {
			return "admin/update-product";
		}
			
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			if(photo.isEmpty()) {
				product.setPhoto(p.getPhoto());
			}
			else {
				try {
					String path = context.getRealPath("/resources/client/img/product/" + photo.getOriginalFilename());
					photo.transferTo(new File(path));
					product.setPhoto(photo.getOriginalFilename());
				} catch (Exception e) {
					model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
							+ "					  Lỗi upload file ảnh!\r\n"
							+ "					</div>");
					return "admin/update-product";
				}
			}
			
			product.setCreated(p.getCreated());
			ss.update(product);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Cập nhật thành công!\r\n"
					+ "					</div>");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Cập nhật thất bại!\r\n"
					+ "					</div>");
			return "admin/update-product";
		}
		p = null;
		return "admin/update-product";
	}
	
	// xóa sản phẩm
	@RequestMapping(value="delete/{id}" , method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") Integer id) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.Id = :id";
		Query query = ss.createQuery(hql);
		query.setParameter("id", id);
		Product product = (Product) query.uniqueResult();
		model.addAttribute("product", product);
		return "admin/delete-product";
	}
	
	@RequestMapping(value="delete" , method = RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("product") Product product, @RequestParam("attachment") MultipartFile photo, BindingResult errors) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			
			ss.delete(product);
			t.commit();
			model.addAttribute("msg", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "					  Xóa thành công!\r\n"
					+ "					</div>");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("msg", "<div class=\"alert alert-danger\" role=\"alert\">\r\n"
					+ "					  Xóa thất bại!\r\n"
					+ "					</div>");
			return "admin/delete-product";
		}
		
		return "redirect:/admin/product/list.htm";
	}
	
	@RequestMapping("list")
	public String list(ModelMap model) {
		Session ss = factory.getCurrentSession();
		String hql = "FROM Product p ORDER BY Id DESC";
		Query query = ss.createQuery(hql);
		List<Product> list = query.list();
		model.addAttribute("lstPro", list);
		return "admin/list-product";
	}
	
	// các hàm xử lý
	public boolean create(Product product) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		try {
			product.setCreated(new Date());
			ss.save(product);
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
	
	@ModelAttribute("lstCate")
	public List<Category> listCate(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = ss.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
}
