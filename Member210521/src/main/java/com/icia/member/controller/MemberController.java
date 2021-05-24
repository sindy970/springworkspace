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
//		������ �̵��� ���� �ּ��̵��̹Ƿ� return���� String���� �ش�.
		System.out.println("joinpage�������� �̵�");
		return "memberjoin";
	}

	@RequestMapping(value = "/memberjoin")
	public ModelAndView memberjoin(@ModelAttribute MemberDTO dto) {
		// ȸ������ ������
		mav = ms.memberjoinInsert(dto);
		System.out.println(dto.toString());
		return mav;
	}

	@RequestMapping(value = "/loginpage")
	public String loginpage() {
//		�α��� �������� �̵��ϱ� ���� �޼ҵ�
		System.out.println("loginpage�������� �̵�");
		return "memberlogin";
	}

	@RequestMapping(value = "/memberlogin")
	public String memberlogin() {
		// �α��� ������
		return "home";
	}

//	@RequestMapping(value = "/listpage")
//	public String listpage() {
//		System.out.println("listpage�������� �̵�");
//		return "memberlist";
//	}

	@RequestMapping(value = "/listpage")
	public ModelAndView memberlist() {
//		��üȸ�� ��� ����ϱ� ���� �޼ҵ�
		System.out.println("memberlist�������� �̵�");
		mav = ms.memberlist();
		return mav;
	}
	
	@RequestMapping(value = "/memberview")
	public ModelAndView memberview(@RequestParam("mid") String mid){
		mav = ms.memberView(mid);
		return mav;
	}

}
