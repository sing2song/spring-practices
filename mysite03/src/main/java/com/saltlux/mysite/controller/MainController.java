package com.saltlux.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.saltlux.mysite.security.Auth;

@Controller
public class MainController {
	@RequestMapping("")
	public String index() {
		return "main/index";
	}
}