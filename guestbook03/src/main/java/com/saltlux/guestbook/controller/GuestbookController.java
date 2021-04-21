package com.saltlux.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.guestbook.repository.GuestbookRepository;
import com.saltlux.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookRepository guestbookRepository;	
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
		return "/WEB-INF/views/index.jsp";
	}

	@RequestMapping("/deleteform")
	public String delete(@RequestParam("no") String no, Model model) {
		model.addAttribute("no",no);
		return "/WEB-INF/views/deleteform.jsp";
	}

	@RequestMapping("/delete")
	public String delete(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
}
