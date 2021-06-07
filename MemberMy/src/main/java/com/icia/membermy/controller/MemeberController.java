package com.icia.membermy.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membermy.dto.MemberDTO;
import com.icia.membermy.service.MemberService;

@Controller
public class MemeberController {

	@Autowired
	private MemberService ms;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;
	
	//회원가입페이지로 이동
	@RequestMapping(value = "/joinpage")
	public String joinPage() {
		return "memberjoin";
	}
	
	//회원 정보 입력 페이지
	@RequestMapping(value="/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO mdto) throws IllegalStateException, IOException {
		mav = ms.memberJoin(mdto);
		return mav;
	}
	
	//아이디 확인을 위한 주소 호출
	@RequestMapping(value="/idcheck")
	public @ResponseBody String idCheck(@RequestParam("mid") String mid) {
		String result = ms.idCheck(mid);
		return result;
	}
	
	//로그인페이지로 이동
	@RequestMapping(value="/loginpage")
	public String memberLogin() {
		return "memberlogin";
	}
	
	//로그인 정보 입력 페이지
	@RequestMapping(value="/memberlogin")
	public ModelAndView memberlogin(@ModelAttribute MemberDTO member) {
			mav = ms.memberLogin(member);
			return mav;
		}

	
	//회원 목록을 출력해주는 페이지
	@RequestMapping(value="/memberlist")
	public ModelAndView memberList(@ModelAttribute MemberDTO mdto) {
		mav = ms.memberList(mdto);
		return mav;
	}
	
	//해당 회원에 관한 상세 정보 출력 페이지
	@RequestMapping(value = "/memberview")
	public ModelAndView memberview(@RequestParam("mnumber") int mnumber){
		mav = ms.memberView(mnumber);
		return mav;
	}
	
	//ajax를 이용한 상세 정보 출력 
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mnumber") int mnumber) {
		MemberDTO memberdto  = ms.memberViewAjax(mnumber);
		return memberdto;
	}
	
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout() {
		session.invalidate();
		return "home";
	}
	
	//회원정보수정페이지
	@RequestMapping(value="/memberupdate")
	public ModelAndView memberUpdate(@RequestParam("mnumber") int mnumber) {
		mav = ms.memberUpdate(mnumber);
		return mav;
	}
	
	@RequestMapping(value = "/updateprocess")
	public ModelAndView updatdProcess(@ModelAttribute MemberDTO memberdto) {
		mav = ms.updateProcess(memberdto);
		return mav;
	}
	
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mnumber") int mnumber) {
		mav = ms.memberDelete(mnumber);
		return mav;
	}
	
}
