package com.doHelp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.LiveRedis;
import com.doHelp.model.User;
import com.doHelp.model.UserRedis;
import com.doHelp.service.LiveService;
import com.doHelp.service.UserService;
import com.doHelp.service.UserServices;

/**
 * 注册 控制层
 * 
 * @author Administrator
 *
 */
@RestController
@EnableAutoConfiguration
public class RegisterController {

	@Autowired
	UserService userService;
	
	@Autowired
	LiveService liveService;

	@Autowired
	private UserServices userServices;

	
	@RequestMapping(value = "/toRegister", method = RequestMethod.GET)
	//@RequestMapping(value={"/",""},method={RequestMethod.POST,RequestMethod.GET},produces = "application/json; charset=UTF-8")
 //如果返回类型的是一个 Class， value 是请求的映射集合， method是请求格式的集合，produces 是返回数据格式。
	public ModelAndView toRegister(ModelMap model) { 
		/**
		 * ModelMap对象主要用于传递控制方法处理数据到结果页面，也就是说我们把结果页面上需要的数据放到ModelMap对象中即可
		 * 他的作用类似于request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据。通过以下方法向页面传递参数： 
         * addAttribute(String key,Object value);
		 * 在页面上可以通过el变量方式$key或者bboss的一系列数据展示标签获取并展示modelmap中的数据。 
         * modelmap本身不能设置页面跳转的url地址别名或者物理跳转地址，那么我们可以通过控制器方法的返回值来设置跳转url
         * 地址别名或者物理跳转地址。
		 * **/
		// 跳转到注册页面前创建一个新的UserRedis对象到页面，用于存储用户信息
		User user = new User();
		model.addAttribute("user", user); 
		return new ModelAndView("register");
	}

	// @ModelAttribute(value = "user") UserRedis user 注释方法参数，参数user的值来源与model属性
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView DoRegister(@ModelAttribute(value = "user") User user, HttpSession session,ModelMap model) throws Exception {
		Date date =new Date();
		SimpleDateFormat from =new SimpleDateFormat("yyyy-MM-dd");
		user.setUser_createAt(from.format(date));
		user.toString();
		userService.save(user);
		userServices.save(user);
		return new ModelAndView("login");
	}
	@RequestMapping(value="/dochange",method= RequestMethod.POST)
	public ModelAndView doChange(@ModelAttribute(value="user") User user,HttpSession session,ModelMap model)throws Exception{
		User  users =(User)session.getAttribute("userRedis");
		user.setUser_phone(users.getUser_phone());
		user.setUser_id(users.getUser_id());
		user.setUser_password(users.getUser_password());
		userServices.update(user);
		session.setAttribute("userRedis", user);
		((ModelMap) model).addAttribute("user",user);
		return new ModelAndView("main");
	
}
	@RequestMapping(value="/dochangePassWord",method= RequestMethod.POST)
	public ModelAndView doChangePassWord(@ModelAttribute(value="user") User user,HttpSession session,ModelMap model)throws Exception{
		User  users =(User)session.getAttribute("userRedis");
		if(users.getUser_password().equals(user.getUser_rpassWord())) {
			users.setUser_password(user.getUser_password());
		    userService.delete(users);
		    userService.save(users);
		    userServices.updatePassWord(users);
			session.setAttribute("userRedis", users);
			((ModelMap) model).addAttribute("user",user);
			return new ModelAndView("login");
		}else
		{
		    userService.delete(users);
		    userService.save(users);
			System.out.println("输入密码错误！！！.");
	     return new ModelAndView("changePassWord");
		}
	}
}