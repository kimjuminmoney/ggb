package com.GGB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.GGB.member.domain.Member;
import com.GGB.member.dto.LoginForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IndexController {
	
	@GetMapping("/")
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			model.addAttribute("loginForm", new LoginForm());
			return "redirect:login";
		}
		Member member = (Member)session.getAttribute("meber");
		model.addAttribute("member", member);
		return "index";
	}
	
	@GetMapping("/index")
	public String index1(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			model.addAttribute("loginForm", new LoginForm());
			return "redirect:login";
		}
		Member member = (Member)session.getAttribute("meber");
		model.addAttribute("member", member);
		return "index";
	}
	//@PostMapping("")
	public String indexPost(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Object member = session.getAttribute("member");
		if(member == null) {
			model.addAttribute("loginForm", new LoginForm());
			return "login";
		}
		return "index";
	}
	
	@GetMapping("/content")
	public String content() {
		return "content";
	}

}
