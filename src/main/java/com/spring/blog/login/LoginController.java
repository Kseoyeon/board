package com.spring.blog.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.blog.user.dto.User;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm(Model model) throws Exception {
		model.addAttribute("user", new User());
		return "login/login";
	}
}
