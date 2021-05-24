package com.icia.member.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;

	private ModelAndView mav;

	@RequestMapping(value = "/joinpage")
	public String joinpage() {
//		페이지 이동을 위한 주소이동이므로 return값을 String으로 준다.
		System.out.println("joinpage페이지로 이동");
		return "memberjoin";
	}

	@RequestMapping(value = "/memberjoin")
	public ModelAndView memberjoin(@ModelAttribute MemberDTO dto) {
		// 회원가입 페이지
		mav = ms.memberjoinInsert(dto);
		System.out.println(dto.toString());
		return mav;
	}

	@RequestMapping(value = "/loginpage")
	public String loginpage() {
//		로그인 페이지로 이동하기 위한 메소드
		System.out.println("loginpage페이지로 이동");
		return "memberlogin";
	}

	@RequestMapping(value = "/memberlogin")
	public String memberlogin() {
		// 로그인 페이지
		return "home";
	}

//	@RequestMapping(value = "/listpage")
//	public String listpage() {
//		System.out.println("listpage페이지로 이동");
//		return "memberlist";
//	}

	@RequestMapping(value = "/listpage")
	public ModelAndView memberlist() {
//		전체회원 목록 출력하기 위한 메소드
		System.out.println("memberlist페이지로 이동");
		mav = ms.memberlist();
		return mav;
	}
	
	@RequestMapping(value = "/memberview")
	public ModelAndView memberview(@RequestParam("mid") String mid){
		mav = ms.memberView(mid);
		return mav;
	}

}
