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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.bean.Cart;
import shop.entity.Oder;
import shop.entity.OderDetail;
import shop.entity.Status;
import shop.entity.User;

@Controller
@Transactional
public class CheckoutController {
	HashMap<Integer, Cart> cart = null;
	@Autowired
	SessionFactory factory;

	@RequestMapping(value="xac-nhan-mua-hang", method = RequestMethod.GET)
	public String blillingDetail(HttpSession session, HttpServletRequest request, ModelMap model) {
		session = request.getSession();
		User u = new User();
		u = (User) session.getAttribute("mUser");
		model.addAttribute("user", u);
		
		return "client/checkout";
	}
	
	@RequestMapping(value="xac-nhan-mua-hang", method = RequestMethod.POST)
	public String checkout(HttpSession ss, HttpServletRequest request, ModelMap model, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		ss = request.getSession();
		User u = new User();
		u = (User) ss.getAttribute("mUser");
		HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>)ss.getAttribute("cart");
		
		Oder oder = new Oder();
		
		Status stt = getStatus(1);
		Oder o = null;
		try {
			oder.setUser(u);
			oder.setCreated(new Date());
			oder.setStatus(stt);
			session.save(oder);
			t.commit();
			o = getOder(oder.getId());
			
		} catch (Exception e) {
			t.rollback();
		}
		finally {
			//session.close();
		}
		
		if(o != null) {
			for(Map.Entry<Integer, Cart> itemCart : cart.entrySet()) {
				t = session.beginTransaction();
				OderDetail oderDetail = new OderDetail();
				try {
					oderDetail.setOder(o);
					oderDetail.setProduct(itemCart.getValue().getProduct());
					oderDetail.setQuantity(itemCart.getValue().getQuantity());
					oderDetail.setUnitPrice(itemCart.getValue().getTotalPrice());
					session.save(oderDetail);
					t.commit();
				} catch (Exception e) {
					t.rollback();
				}
				
			}
		}
		ss.setAttribute("cart", this.cart);
		ss.setAttribute("totalQuantityCart", 0);
		ss.setAttribute("totalPriceCart", 0);
		model.addAttribute("msg", "Đặt hàng thành công! Đang chờ xác nhận.");
		return "client/success-checkout";
	}
	
	@ModelAttribute("menu")
	public List<String> menu(ModelMap model){
		List<String> menu = new ArrayList<String>();
		menu.add("Album");
		menu.add("Magazine");
		menu.add("Photobook");
		menu.add("Beauty");
		menu.add("Fashion");
		model.addAttribute("menu", menu);
		return menu;
	}
	
	public Status getStatus(int stt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Status WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", stt);
		Status status = (Status) query.uniqueResult();
		return status;
	}
	
	public Oder getOder(int stt) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Oder WHERE Id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", stt);
		Oder oder = (Oder) query.uniqueResult();
		return oder;
	}
}
