package com.saltlux.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("")
	public String index() {
		return "/WEB-INF/view/main/index.jsp";
	}
}