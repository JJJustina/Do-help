package com.doHelp.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.User;

public class MyInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(MyInterceptor.class);// 日志输出

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userRedis");
		// 判断用户ID是否存在，不存在就跳转到登录界面
		if (user != null) {
			return true;
		} else {
			logger.info("------:跳转到login页面！");
			response.sendRedirect(request.getContextPath() + "/login");
		}
		logger.info("------preHandle------");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
