package com.icia.member.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dao.MemberDAO;
import com.icia.member.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mdao;

	private ModelAndView mav;

	public ModelAndView memberjoinInsert(MemberDTO dto) {
		mav = new ModelAndView();
		int insertResult = 0;
		insertResult = mdao.memberjoinInsert(dto);
		if (insertResult > 0) {
//			insertResult값이 0보다 크다는 것은 insert를 성공했다는 의미이기 때문에 회원가입이 완료된것으로 판단
			mav.setViewName("memberlogin");
		} else {
//			insertResult가 0이라는 것은 insert를 실패했다는 의미이다.
			mav.setViewName("joinfail");
		}
		return mav;
	}

	public ModelAndView memberlist() {
		mav = new ModelAndView();
		List<MemberDTO> mdtoList = mdao.memberlist();

		mav.setViewName("memberlist");
		mav.addObject("mdtoList", mdtoList);

		return mav;
	}

	public ModelAndView memberView(String mid) {
		mav = new ModelAndView();
		
//		한명 회원에 대한 정보만 필요하기 때문에 DTO타입의 객체로 리턴을 받음.
		MemberDTO member = mdao.memberView(mid);
		
//		DB조회 결과를 member에 받았고 member를 담아서 memberview.jsp로 가야된다.
		mav.addObject("result", member);
		mav.setViewName("memberview");
		return mav;
	}



	

}
