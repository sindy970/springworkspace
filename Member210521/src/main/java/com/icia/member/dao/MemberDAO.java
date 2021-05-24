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
//		sql.insert함수 자체내에서 return값으로 행의 갯수를 준다.
//		memberjoininsertDB호출 결과를 받아서 insert성공 여부에 따라 처리한다.

		return sql.insert("member.memberjoininsertDB", dto);
	}

	public List<MemberDTO> memberlist() {
		return sql.selectList("member.memberlistDB");
	}


	public MemberDTO memberView(String mid) {
//		조회 결과가 한줄이기 때문에 selectOne함수를 사용한다.
		return sql.selectOne("member.memberview", mid);
	}

	

}
