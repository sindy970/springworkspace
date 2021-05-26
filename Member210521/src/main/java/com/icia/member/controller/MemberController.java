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
	public ModelAndView memberlogin(@ModelAttribute MemberDTO member) {
	//로그인 페이지
		System.out.println("login 메소드" + member.toString());
		mav = ms.memberLogin(member);
//		member객체에는 memberlogin.jsp에서 입력한 mid, mpassword값만 담겨 있다.
		return mav;
	}

//	@RequestMapping(value = "/memberlogin")
//	public String memberlogin() {
//		// 로그인 페이지
//		return "home";
//	}

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
	
//	로그아웃 처리 메소드
	@RequestMapping(value = "/logout")
	public String logout() {
		//로그아웃은 세션에 저장된 내용을 지운다는 개념이다. 즉,로그인한 아이디 or 비밀번호를 지운다는 개념이다.
		session.invalidate();
		//invalidate메소드는 세션에 있는 값들을 없애는 메소드 이다.
		
		return "home";
	}
	
	
	//회원정보수정
	@RequestMapping(value="/memberupdate")
	public ModelAndView update() {
		mav = ms.update();
		return mav;
	}
	
	//회원정보 수정 처리
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
	
	//아이디 중복 확인 메소드
	//@ResponsBody : 리턴값을 제대로 memberjoin.jsp로 보내기 위해서 사용함
	@RequestMapping(value="/idcheck")
	public @ResponseBody String idCheck(@RequestParam("mid") String mid) {
		System.out.println("idCheck 메소드 호출");
		System.out.println("입력 id값 : " + mid);
		String result = ms.idCheck(mid);
		return result;
	}
	
	
	//ajax를 이용한 조회 처리를 위한 메소드
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		System.out.println("memberViewAjax메소드 호출됨");
		System.out.println("전달 id값 : " + mid);
		
		MemberDTO member  = ms.memberViewAjax(mid);
		return member;
	}

}
