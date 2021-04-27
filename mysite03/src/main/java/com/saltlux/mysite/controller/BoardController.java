package com.saltlux.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.saltlux.mysite.service.BoardService;
import com.saltlux.mysite.service.UserService;
import com.saltlux.mysite.vo.BoardVo;
import com.saltlux.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		// 접근제어
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		model.addAttribute("userVo", userVo);
		
		return "board/writeform";
	}

	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String join(BoardVo vo) {
		boardService.write(vo);
		return "redirect:/user/joinsuccess";	
	}
	
	@RequestMapping("/look")
	public String joinsuccess() {
		return "board/look";	
	}
}
