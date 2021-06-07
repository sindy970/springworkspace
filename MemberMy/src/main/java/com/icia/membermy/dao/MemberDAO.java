package com.icia.membermy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.membermy.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;

	public int memberJoin(MemberDTO mdto) {
		return sql.insert("member.memberjoin", mdto);
	}

	public void memberProfile(MemberDTO mdto) {
		sql.insert("member.memberjoin", mdto);
	}

	public String idCheck(String mid) {
		return sql.selectOne("member.idcheck", mid);
	}

	public String memberLogin(MemberDTO member) {
		return sql.selectOne("member.memberlogin", member);
	}

	public List<MemberDTO> memberList(MemberDTO mdto) {
		return sql.selectList("member.memberlist");
	}

	public MemberDTO memberView(int mnumber) {
		return sql.selectOne("member.memberview", mnumber);
	}

	public int updateProcess(MemberDTO memberdto) {
		return sql.update("member.memberupdate", memberdto);
	}

	public void memberDelete(int mnumber) {
		sql.delete("member.memberdelete", mnumber);
	}


	



	
	

}
