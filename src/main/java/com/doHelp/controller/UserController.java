package com.doHelp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.doHelp.model.User;
import com.doHelp.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/userList")
	public ModelAndView list(Model model) {
		List<User> list = userService.getAll();
		model.addAttribute("users", list);
		return new ModelAndView("user/userList");
	}
	
	
	
}
