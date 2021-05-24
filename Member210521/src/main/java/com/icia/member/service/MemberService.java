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
//			insertResult���� 0���� ũ�ٴ� ���� insert�� �����ߴٴ� �ǹ��̱� ������ ȸ�������� �Ϸ�Ȱ����� �Ǵ�
			mav.setViewName("memberlogin");
		} else {
//			insertResult�� 0�̶�� ���� insert�� �����ߴٴ� �ǹ��̴�.
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
		
//		�Ѹ� ȸ���� ���� ������ �ʿ��ϱ� ������ DTOŸ���� ��ü�� ������ ����.
		MemberDTO member = mdao.memberView(mid);
		
//		DB��ȸ ����� member�� �޾Ұ� member�� ��Ƽ� memberview.jsp�� ���ߵȴ�.
		mav.addObject("result", member);
		mav.setViewName("memberview");
		return mav;
	}



	

}
