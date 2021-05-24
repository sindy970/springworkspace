package com.icia.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.icia.member.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;

	private ModelAndView mav;

	public int memberjoinInsert(MemberDTO dto) {
//		mapper namespace => member, insert id => memberjoininsertDB
//		sql.insert�Լ� ��ü������ return������ ���� ������ �ش�.
//		memberjoininsertDBȣ�� ����� �޾Ƽ� insert���� ���ο� ���� ó���Ѵ�.

		return sql.insert("member.memberjoininsertDB", dto);
	}

	public List<MemberDTO> memberlist() {
		return sql.selectList("member.memberlistDB");
	}


	public MemberDTO memberView(String mid) {
//		��ȸ ����� �����̱� ������ selectOne�Լ��� ����Ѵ�.
		return sql.selectOne("member.memberview", mid);
	}

	

}
