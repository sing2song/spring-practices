package com.saltlux.mysite.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltlux.mysite.security.Auth;
import com.saltlux.mysite.security.AuthUser;
import com.saltlux.mysite.service.UserService;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";	
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";	
	}

	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";	
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";	
	}

	/*
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVo vo, HttpSession session) {
		UserVo authUser = userService.getUser(vo);
		if(authUser == null) {
			return "redirect:/user/login?result=fail";
		}
		
		//로그인 처리
		System.out.println("로그인정보 : "+authUser);
		session.setAttribute("authUser", authUser);
		return "redirect:/";	
	}
	

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 접근제어
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		//로그아웃 처리 \
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
*/
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		Long no = authUser.getNo();
		
		UserVo userVo = userService.getUser(no);
		System.out.println("수정정보 : "+userVo);
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}

	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo vo) {		
		Long no = authUser.getNo();
		vo.setNo(no);
		userService.update(vo);
		System.out.println(vo);
		
		return "redirect:/user/update";
	}
	
	/*
	@ExceptionHandler(Exception.class)
	public String handleException() {
		//로그작업
		return "error/exception";
	}
	*/
}
