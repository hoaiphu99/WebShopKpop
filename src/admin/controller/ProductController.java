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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	// thêm sản phẩm
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("product", new Product());
		model.addAttribute("listCate", listCate());
		return "admin/add-product";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("product") Product product, @RequestParam("attachment") MultipartFile photo ) {
		
		if(photo.isEmpty()) {
			model.addAttribute("msgEmpty", "Vui lòng chọn file!");
		}
		try {
			String path = context.getRealPath("/resources/client/img/" + photo.getOriginalFilename());
			photo.transferTo(new File(path));
		} catch (Exception e) {
			model.addAttribute("failFile", "Lỗi upload file ảnh!");
		}
		product.setPhoto(photo.getOriginalFilename());
		if(!create(product)) {
			model.addAttribute("failAdd", "Tạo thất bại. Tên sản phẩm không được trùng!");
			model.addAttribute("product", new Product());
			model.addAttribute("listCate", listCate());
		}
		
		model.addAttribute("successAdd", "Tạo thành công.");
		model.addAttribute("product", new Product());
		model.addAttribute("listCate", listCate());
		
		return "admin/add-product";
	}
	
	@RequestMapping("list")
	public String list(ModelMap model) {
		Session ss = factory.getCurrentSession();
		String hql = "SELECT p.Name, p.Description, p.Price, p.Discount, p.Quantity, p.Created, p.category.Name "
					+ "FROM Product p";
		Query query = ss.createQuery(hql);
		List<Object[]> list = query.list();
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
	
	public List<Category> listCate(){
		Session ss = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = ss.createQuery(hql);
		List<Category> list = query.list();
		for (Category category : list) {
			System.out.print(category.getName());
		}
		return list;
	}
}
