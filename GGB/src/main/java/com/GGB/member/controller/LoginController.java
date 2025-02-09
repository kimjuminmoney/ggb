package com.GGB.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.GGB.member.domain.Member;
import com.GGB.member.dto.LoginForm;
import com.GGB.member.dto.SingupForm;
import com.GGB.member.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class LoginController {
	
	LoginService loginService;
	
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@GetMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Valid LoginForm loginFrom, HttpServletRequest request) {
		Member member = loginService.login(loginFrom);
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
		log.error("통과");
		return "redirect:index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/login";
	}
	@GetMapping("/singup")
	public String singup(HttpServletRequest request, Model model) {
		model.addAttribute("singupForm", new SingupForm());
		log.error("회원가입");
		return "singup";
	}
	@PostMapping("/singup")
	public String singup(@Valid SingupForm singupFrom, HttpServletRequest request, Model model) {
		log.error("회원가입 요청");
		log.error(singupFrom.toString());
		HttpSession session = request.getSession();
		String chkId = (String) session.getAttribute("chkId");
		if(!chkId.equals(singupFrom.getSingupId())) {
			
		}
		Member member = loginService.addMember(singupFrom);
		session.setAttribute("MEMBER", member);
		return "singup";
	}
	@GetMapping("/chkId")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> chkId(@RequestParam String id, HttpServletRequest request, Model model) {
		boolean flag = loginService.getLoginId(id);
		HttpSession session = request.getSession();
		session.setAttribute("chkId", id);
		Map<String, Object> response = new HashMap<>();
		//중복ID체크라 false가 true로 반환됨
		response.put("flag", !flag);
		return ResponseEntity.ok(response);
	}
	
	

}
