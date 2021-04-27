package com.saltlux.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mysite.service.GuestbookService;
import com.saltlux.mysite.vo.Guestbook02Vo;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<Guestbook02Vo> list = guestbookService.findAll();
		model.addAttribute("list",list);
		
		return "guestbook/index";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		List<Guestbook02Vo> list = guestbookService.findAll();
		model.addAttribute("list",list);
		
		return "guestbook/list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform(@RequestParam Long no, Model model) {
		System.out.println("no : "+no);
		model.addAttribute("no",no);
		return "guestbook/deleteform";	
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam Long no ,@RequestParam String password,Model model) {
		Guestbook02Vo vo = new Guestbook02Vo();
		vo.setNo(no);
		vo.setPassword(password);
		
		if(guestbookService.delete(vo))
			return "redirect:/guestbook/index";	
		else {
			model.addAttribute("fail","authResult");
			return "guestbook/deleteform";
		}
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(Guestbook02Vo vo) {
		guestbookService.add(vo);
		return "/guestbook";	
	}
	
	
}
