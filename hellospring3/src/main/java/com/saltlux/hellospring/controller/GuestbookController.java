package com.saltlux.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @authoer ssong
 * 
 * @RequestMapping
 * Type(Class) 단독매핑
 * */

@Controller
@RequestMapping("/guestbook/*")
public class GuestbookController {
	
	
	@RequestMapping("/list")
	public String list() {
		return "GuestbookController:list()";
	}


	@RequestMapping("/delete")
	public String delete() {
		return "GuestbookController:delete()";
	}
	
}
