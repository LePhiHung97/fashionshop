package com.hunglp.fashionshop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("")
public class RestController {
	@GetMapping("/api/user")
	@ResponseBody
	public String userAPI() {
		return "THIS IS USER";
	}

	@GetMapping("/api/admin")
	@ResponseBody
	public String adminAPI() {
		return "THIS IS ADMIN";
	}

}
