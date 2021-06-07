package com.icia.membermy.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membermy.dao.MemberDAO;
import com.icia.membermy.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mdao;

	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;

	public ModelAndView memberJoin(MemberDTO mdto) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		
		MultipartFile mprofile = mdto.getMprofile();
		
		String mprofilename = mprofile.getOriginalFilename();
		
		mprofilename = System.currentTimeMillis()+"-"+mprofilename;
		
		String savePath = "E:\\soure_hsh\\정리본\\44일차(spring회원제게시판)\\MemberMy\\src\\main\\webapp\\resources\\upload\\"+mprofilename;

		if(!mprofile.isEmpty()) {
			mprofile.transferTo(new File(savePath));
		} 
		
		mdto.setMprofilename(mprofilename);
		
		mdao.memberProfile(mdto);
		
		mav.setViewName("redirect:/membermyMain");
		return mav;
	}

	public String idCheck(String mid) {
		String checkResult = mdao.idCheck(mid);
		
		String result = "";
		if(checkResult == null) {
			result = "ok";
		} else {
			result = "no";
		}
		
		return result;
	}
	
	public ModelAndView memberLogin(MemberDTO member) {
		mav = new ModelAndView();
		String loginId = mdao.memberLogin(member);
		if(loginId != null) {
			session.setAttribute("loginId", loginId);
			if(loginId.equals("admin")) {
				mav.setViewName("redirect:/memberlist");
			} else {
				mav.addObject("member", loginId);
				mav.setViewName("membermyMain");
			}
		} else {
			mav.setViewName("memberlogin");
		}
		return mav;
	}

	public ModelAndView memberList(MemberDTO mdto) {
		mav = new ModelAndView();
		List<MemberDTO> memberList = mdao.memberList(mdto);
		
		mav.addObject("memberList",memberList);
		mav.setViewName("memberlist");
		return mav;
	}

	public ModelAndView memberView(int mnumber) {
		mav = new ModelAndView();
		MemberDTO memberdto = mdao.memberView(mnumber);
		
		mav.addObject("memberdto", memberdto);
		mav.setViewName("memberview");
		return mav;
	}

	public MemberDTO memberViewAjax(int mnumber) {
		MemberDTO memberdto = mdao.memberView(mnumber);
		return memberdto;
	}

	public ModelAndView memberUpdate(int mnumber) {
		mav = new ModelAndView();
		MemberDTO memberdto = mdao.memberView(mnumber);
		mav.addObject("memberupdate", memberdto);
		mav.setViewName("memberupdate");
		return mav;
	}
	
	public ModelAndView updateProcess(MemberDTO memberdto) {
		mav = new ModelAndView();
		int updateResult = mdao.updateProcess(memberdto);
		
		if(updateResult > 0) {
			mav.setViewName("redirect:/memberview?mnumber"+ memberdto.getMnumber());
		} else {
			mav.setViewName("memberlist");
		}
		return mav;
	}
	

	public ModelAndView memberDelete(int mnumber) {
		mav = new ModelAndView();
		mdao.memberDelete(mnumber);
		mav.setViewName("redirect:/memberlist");
		return mav;
	}


	



	
	
	

}
