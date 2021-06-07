package com.icia.membermy.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.membermy.dto.BoardDTO;
import com.icia.membermy.dto.PageDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void boardWriteFile(BoardDTO board) {
		sql.insert("board.boardwritefile", board);
	}

	public List<BoardDTO> boardList() {
		return sql.selectList("board.boardlist");
	}

	public BoardDTO boardView(int bnumber) {
		return sql.selectOne("board.boardview", bnumber);
	}

	public int updateProcess(BoardDTO board) {
		return sql.update("board.boardupdate", board);
	}

	public int boardDelete(int bnumber) {
		return sql.delete("board.boarddelete", bnumber);
	}

	public int listCount() {
		return sql.selectOne("board.listcount");
	}

	public List<BoardDTO> boardPaging(PageDTO paging) {
		return sql.selectList("board.boardpaging", paging);
	}

	public List<BoardDTO> boardSearch(Map<String, String> searchMap) {
		return sql.selectList("board.boardsearch", searchMap);
	}

	

	
}
