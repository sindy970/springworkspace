package com.icia.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dto.BoardDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	private ModelAndView mav;

	public int boardwriteInsert(BoardDTO bdto) {
		return sql.insert("board.boardwriteInsertDB", bdto);
	}

	public List<BoardDTO> boardList(BoardDTO bdto) {
		return sql.selectList("board.boardlistDB", bdto);
	}



	public BoardDTO boardView(int bnumber) {
		return sql.selectOne("board.boardviewDB",bnumber);
	}



	

	
	
	

}
