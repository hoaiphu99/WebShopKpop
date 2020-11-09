package shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import shop.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("mUser") == null) {
			respone.sendRedirect(request.getContextPath() + "/login.htm");
			return false;
		}
		User u = new User();
		u = (User) session.getAttribute("mUser");
		if(u.getUserRole().equals("user")) {
			respone.sendRedirect(request.getContextPath() + "/index.htm");
			return false;
		}
		return true;
	}

}
