package com.saltlux.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.guestbook.repository.GuestbookRepository;
import com.saltlux.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookRepository guestbookRepository;	
	
	@RequestMapping("")
	public String index(Model model) {
		System.out.println("여기왔나용");
		List<GuestbookVo> list = guestbookRepository.findAll();
		model.addAttribute("list", list);
	
		System.out.println(list);
		return "/WEB-INF/views/index.jsp";
	}

	@RequestMapping(value="/delete/{no}",method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		System.out.println("no="+no);
		model.addAttribute("no",no);
		return "/WEB-INF/views/deleteform.jsp";
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(GuestbookVo vo) {
		guestbookRepository.delete(vo);
		return "redirect:/";
	}
	
	@RequestMapping("/insert")
	public String insert(GuestbookVo vo) {
		guestbookRepository.insert(vo);
		return "redirect:/";
	}
	
}
