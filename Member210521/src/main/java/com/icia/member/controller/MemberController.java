package com.icia.member.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;
import com.icia.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService ms;

	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;

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
	public ModelAndView memberlogin(@ModelAttribute MemberDTO member) {
	//�α��� ������
		System.out.println("login �޼ҵ�" + member.toString());
		mav = ms.memberLogin(member);
//		member��ü���� memberlogin.jsp���� �Է��� mid, mpassword���� ��� �ִ�.
		return mav;
	}

//	@RequestMapping(value = "/memberlogin")
//	public String memberlogin() {
//		// �α��� ������
//		return "home";
//	}

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
	
//	�α׾ƿ� ó�� �޼ҵ�
	@RequestMapping(value = "/logout")
	public String logout() {
		//�α׾ƿ��� ���ǿ� ����� ������ ����ٴ� �����̴�. ��,�α����� ���̵� or ��й�ȣ�� ����ٴ� �����̴�.
		session.invalidate();
		//invalidate�޼ҵ�� ���ǿ� �ִ� ������ ���ִ� �޼ҵ� �̴�.
		
		return "home";
	}
	
	
	//ȸ����������
	@RequestMapping(value="/memberupdate")
	public ModelAndView update() {
		mav = ms.update();
		return mav;
	}
	
	//ȸ������ ���� ó��
	@RequestMapping(value = "/updateprocess")
	public ModelAndView updateProcess(@ModelAttribute MemberDTO member) {
		mav = ms.updateProcess(member);
		return mav;
	}
	
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav = ms.memberDelete(mid);
		return mav;
	}
	
	//���̵� �ߺ� Ȯ�� �޼ҵ�
	//@ResponsBody : ���ϰ��� ����� memberjoin.jsp�� ������ ���ؼ� �����
	@RequestMapping(value="/idcheck")
	public @ResponseBody String idCheck(@RequestParam("mid") String mid) {
		System.out.println("idCheck �޼ҵ� ȣ��");
		System.out.println("�Է� id�� : " + mid);
		String result = ms.idCheck(mid);
		return result;
	}
	
	
	//ajax�� �̿��� ��ȸ ó���� ���� �޼ҵ�
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("memberViewAjax�޼ҵ� ȣ���");
		System.out.println("���� id�� : " + mid);
		
		MemberDTO member  = ms.memberViewAjax(mid);
		return member;
	}

}
