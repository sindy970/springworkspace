package com.icia.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	//세션을 사용하기 위한 세션 객체 선언
	@Autowired
	private HttpSession session;

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

	public ModelAndView memberLogin(MemberDTO member) {
		/*
		 * 로그인 처리 로직 개념
		 * 사용자가 memberlogin.jsp에서 입력한 아이디, 비밀번호가 회원가입할 때 DB에 저장된 아이디,비번과 일치하는지를 판단하여
		 * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
		 * 
		 * 입력한 값을 가져가서 DB에서 역으로 조회를 해서 결과가 나오면 성공 그렇지 않으면 실패하는 과정을 하는 것이다.
		 */
		mav = new ModelAndView();
		String loginId = mdao.memberLogin(member);
		
//		loginId에 값이 있다면 아이디,비밀번호가 모두 일치한다는 것이다. (로그인 성공)
//		loginId에 값이 없다면 아이디 or 비밀번호 혹은 모두가 틀력다는 것이다. (로그인 실패)
		
//		사용자가 로그인을 하고 나면 로그아웃 또는 브라우저를 닫기 전까지는 로그인을 유지하고 있어야 한다. ==> 세션(session)을 이용하여 기능을 구현한다.
//		일반적으로 로그인 정보(아이디 값)은 세션(session)에 저장을 하도록한다.
//		세션은 서보(톰캣)에서 관리하는 일종의 저장공간이며 세션에 데이터를 저장하게 되면 브라우저를 닫기 전까지는 페이지가 변경되어도 데이터는 유지가 된다.
		
		if(loginId != null) {
//			로그인 성공 처리
//			로그인한 회원의 아이디를 세션에 저장(setAttribute함수를 이용하여 저장한다.)
//			형식 : setAttribute("저장할 변수이름",저장할 값이 들어있는 변수 이름) ==> "변수이름" = 저장할 값이 들어있는 변수이름; 의 형식으로 대입한다고 이해하면 됨
//			세션에 저장한 값은 서버는 구동중이지만 브라우저를 닫았다가 다시 실행하면 자동으로 로그아웃이 된다. 즉 세션의 값이 null로 초기화된다. ex)naver등의 대부분 페이지
//			화면을 닫았다가 재실행 했을 때도 로그인 정보가 그대로인 것을 원하면 세션이 아닌 쿠키라는 것을 이용하면 된다. ex)구글 로그인
			session.setAttribute("loginMember", loginId);
			
			mav.setViewName("membermain");
		} else {
//			로그인 실패처리
			mav.setViewName("memberlogin");
		}
		
		return mav;
	}

	public ModelAndView update() {
		mav = new ModelAndView();
		
		//우변 : 현재 로그인을 한 상태에서 수정요청을 하는 것이기 때문에 세션에 저장된 로그인 아이디값을 가지고 옴
		// 	: 가져와서 loginId변수에 저장
		String loginId = (String) session.getAttribute("loginMember");
		// 로그인을 하고 있는 상태여야만 session.getAttribute값이 제대로 넘어 올 수 있다. 
		//getAttribute의 리턴타입이 object타입 즉 자바에 최상위 클래스 이므로 String형태로 강제 형변환을 해줘야 한다.
		
		//update()메소드는 현재 로그인한 회원의 전체정보를 DB로 부터 가져와서 memberupdate.jsp에 출력하는 것이 목적이기 때문에 memberview메소드를 사용해도 문제 없다.
//		MemberDTO memberUpdate = mdao.memberView(loginId);
		MemberDTO memberUpdate = mdao.update(loginId);
		
		//가져온 데이터를 다시 가지고 jsp페이지로 간다.
		mav.addObject("member123", memberUpdate);
		mav.setViewName("memberupdate");
		return mav;
	}

	public ModelAndView updateProcess(MemberDTO member) {
		mav = new ModelAndView();
		int updateResult = mdao.updateProces(member);
		
		//수정 완료 -> membermain
		//수정 실패 -> updatefail
		if(updateResult > 0) {
			mav.setViewName("membermain");
		} else {
			mav.setViewName("updatefail" );
		}
		return mav;
	}

	public ModelAndView memberDelete(String mid) {
		mav = new ModelAndView();
		mdao.memberDelete(mid);
		
		//수정이 끝나면 memberlist.jsp를 출력
		//memberlist.jsp가 제대로 출력되려면 controller를 거쳐서 DB 조회결과를 가지고 memberlist.jsp로 가야한다.
		//아래와 같이 memberlist.jsp만 출력하게 되면 데이터는 못가져가게 된다.
//		mav.setViewName("memberlist");
		//따라서 컨트롤러의 주소를 요청해야 한다.
		
		//컨트롤러의 주소들 요청하기 위한 방법
		mav.setViewName("redirect:/listpage");
		return mav;
	}

	public String idCheck(String mid) {
		String checkResult = mdao.idCeck(mid);
		/*
		 * checkResult에 값이 담겨 온다면 해당 아이디가 DB에 존재한다는 것(사용불가하다)
		 * checkResult에 값이 없다면 해당 아이디가 DB에 존재하지 않는다는것(사용가능하다)
		 */
		
		String result = "";
		if(checkResult == null) {
			result = "ok";
		} else {
			result = "no";
		}
		
		System.out.println("서비스클래스 체크결과" + result);
		
		return result;
	}

	public MemberDTO memberViewAjax(String mid) {
		MemberDTO member = mdao.memberView(mid);
		//조회하는 내용이기 때문에 만들어놓은 memberView()메소드를 그대로 사용함
		return member;
	}

}
