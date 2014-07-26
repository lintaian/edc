package com.lps.edc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 利用拦截器对后台进行权限的验证
 * @author 林泰安
 */
public class LoginInterceptor implements HandlerInterceptor{
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object arg2, Exception arg3) throws Exception {
		
	}
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2, ModelAndView arg3) throws Exception {
		
	}
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
//		if(req.getSession().getAttribute("user") != null) {
//			return true;
//		} else {
//			String str = req.getServletPath();
//			if("/login".equals(str)) {
//				return true;
//			} else {
//				resp.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"
//						+req.getServerPort()+req.getContextPath()+"/login");
//				return false;
//			}
//		}
		return true;
	}
}
