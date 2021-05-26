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
	
	//������ ����ϱ� ���� ���� ��ü ����
	@Autowired
	private HttpSession session;

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

	public ModelAndView memberLogin(MemberDTO member) {
		/*
		 * �α��� ó�� ���� ����
		 * ����ڰ� memberlogin.jsp���� �Է��� ���̵�, ��й�ȣ�� ȸ�������� �� DB�� ����� ���̵�,����� ��ġ�ϴ����� �Ǵ��Ͽ�
		 * ��ġ�ϸ� �α��� ����, ��ġ���� ������ �α��� ���з� ó��
		 * 
		 * �Է��� ���� �������� DB���� ������ ��ȸ�� �ؼ� ����� ������ ���� �׷��� ������ �����ϴ� ������ �ϴ� ���̴�.
		 */
		mav = new ModelAndView();
		String loginId = mdao.memberLogin(member);
		
//		loginId�� ���� �ִٸ� ���̵�,��й�ȣ�� ��� ��ġ�Ѵٴ� ���̴�. (�α��� ����)
//		loginId�� ���� ���ٸ� ���̵� or ��й�ȣ Ȥ�� ��ΰ� Ʋ�´ٴ� ���̴�. (�α��� ����)
		
//		����ڰ� �α����� �ϰ� ���� �α׾ƿ� �Ǵ� �������� �ݱ� �������� �α����� �����ϰ� �־�� �Ѵ�. ==> ����(session)�� �̿��Ͽ� ����� �����Ѵ�.
//		�Ϲ������� �α��� ����(���̵� ��)�� ����(session)�� ������ �ϵ����Ѵ�.
//		������ ����(��Ĺ)���� �����ϴ� ������ ��������̸� ���ǿ� �����͸� �����ϰ� �Ǹ� �������� �ݱ� �������� �������� ����Ǿ �����ʹ� ������ �ȴ�.
		
		if(loginId != null) {
//			�α��� ���� ó��
//			�α����� ȸ���� ���̵� ���ǿ� ����(setAttribute�Լ��� �̿��Ͽ� �����Ѵ�.)
//			���� : setAttribute("������ �����̸�",������ ���� ����ִ� ���� �̸�) ==> "�����̸�" = ������ ���� ����ִ� �����̸�; �� �������� �����Ѵٰ� �����ϸ� ��
//			���ǿ� ������ ���� ������ ������������ �������� �ݾҴٰ� �ٽ� �����ϸ� �ڵ����� �α׾ƿ��� �ȴ�. �� ������ ���� null�� �ʱ�ȭ�ȴ�. ex)naver���� ��κ� ������
//			ȭ���� �ݾҴٰ� ����� ���� ���� �α��� ������ �״���� ���� ���ϸ� ������ �ƴ� ��Ű��� ���� �̿��ϸ� �ȴ�. ex)���� �α���
			session.setAttribute("loginMember", loginId);
			
			mav.setViewName("membermain");
		} else {
//			�α��� ����ó��
			mav.setViewName("memberlogin");
		}
		
		return mav;
	}

	public ModelAndView update() {
		mav = new ModelAndView();
		
		//�캯 : ���� �α����� �� ���¿��� ������û�� �ϴ� ���̱� ������ ���ǿ� ����� �α��� ���̵��� ������ ��
		// 	: �����ͼ� loginId������ ����
		String loginId = (String) session.getAttribute("loginMember");
		// �α����� �ϰ� �ִ� ���¿��߸� session.getAttribute���� ����� �Ѿ� �� �� �ִ�. 
		//getAttribute�� ����Ÿ���� objectŸ�� �� �ڹٿ� �ֻ��� Ŭ���� �̹Ƿ� String���·� ���� ����ȯ�� ����� �Ѵ�.
		
		//update()�޼ҵ�� ���� �α����� ȸ���� ��ü������ DB�� ���� �����ͼ� memberupdate.jsp�� ����ϴ� ���� �����̱� ������ memberview�޼ҵ带 ����ص� ���� ����.
//		MemberDTO memberUpdate = mdao.memberView(loginId);
		MemberDTO memberUpdate = mdao.update(loginId);
		
		//������ �����͸� �ٽ� ������ jsp�������� ����.
		mav.addObject("member123", memberUpdate);
		mav.setViewName("memberupdate");
		return mav;
	}

	public ModelAndView updateProcess(MemberDTO member) {
		mav = new ModelAndView();
		int updateResult = mdao.updateProces(member);
		
		//���� �Ϸ� -> membermain
		//���� ���� -> updatefail
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
		
		//������ ������ memberlist.jsp�� ���
		//memberlist.jsp�� ����� ��µǷ��� controller�� ���ļ� DB ��ȸ����� ������ memberlist.jsp�� �����Ѵ�.
		//�Ʒ��� ���� memberlist.jsp�� ����ϰ� �Ǹ� �����ʹ� ���������� �ȴ�.
//		mav.setViewName("memberlist");
		//���� ��Ʈ�ѷ��� �ּҸ� ��û�ؾ� �Ѵ�.
		
		//��Ʈ�ѷ��� �ּҵ� ��û�ϱ� ���� ���
		mav.setViewName("redirect:/listpage");
		return mav;
	}

	public String idCheck(String mid) {
		String checkResult = mdao.idCeck(mid);
		/*
		 * checkResult�� ���� ��� �´ٸ� �ش� ���̵� DB�� �����Ѵٴ� ��(���Ұ��ϴ�)
		 * checkResult�� ���� ���ٸ� �ش� ���̵� DB�� �������� �ʴ´ٴ°�(��밡���ϴ�)
		 */
		
		String result = "";
		if(checkResult == null) {
			result = "ok";
		} else {
			result = "no";
		}
		
		System.out.println("����Ŭ���� üũ���" + result);
		
		return result;
	}

	public MemberDTO memberViewAjax(String mid) {
		MemberDTO member = mdao.memberView(mid);
		//��ȸ�ϴ� �����̱� ������ �������� memberView()�޼ҵ带 �״�� �����
		return member;
	}

}
