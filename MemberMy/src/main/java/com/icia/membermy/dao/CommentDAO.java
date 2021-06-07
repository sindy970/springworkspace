package com.icia.membermy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.membermy.dto.CommentDTO;


@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public int commentWrite(CommentDTO comment) {
		return sql.insert("comment.commentwrite", comment);
	}

	public List<CommentDTO> commentList(int cbnumber) {
		return sql.selectList("comment.commentlist", cbnumber);
	}

}
