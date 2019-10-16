package com.hunglp.fashionshop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class BaseController {
	
	@RequestMapping("/")
	public String welcome(ModelMap modelMap) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usernameLogin = authentication.getName();
		System.out.println(usernameLogin);
		if (!usernameLogin.equals("anonymousUser"))
			modelMap.addAttribute("username", usernameLogin);
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String myProfile(ModelMap modelMap,HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String usernameLogin = authentication.getName();
		System.out.println(usernameLogin);
		
		return "home";
	}
	
}
