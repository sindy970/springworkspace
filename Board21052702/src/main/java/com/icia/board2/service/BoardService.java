package com.icia.board2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board2.dao.BoardDAO;
import com.icia.board2.dto.BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	private ModelAndView mav;

	public ModelAndView boardWrite(BoardDTO board) {
		mav = new ModelAndView();
		
		int writeResult = bdao.boardWrite(board);
		
		/* 
		 * 글쓰기 성공 : 목록출력(redirect로 boardlist의 주소를 요청해야 한다.)
		 * 글쓰기 실패 : boardwrite.jsp를 띄움 
		 */ 
		if(writeResult >0) {
			mav.setViewName("redirect:/boardlist");
		} else {
			mav.setViewName("boardwrite");
		}
		return mav;
	}

	public ModelAndView boardList() {
		/*
		 * 목록을 화면에 출력하기만 하면 되기 때문에 매개변수로 값을 가져오거나 받아올 것이 없다.
		 * 따라서 궅이 매개변수를 만들 필요가 없다.
		 */
		mav = new ModelAndView();
		
		List<BoardDTO> boardList = bdao.boardList();
		
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlist");
		return mav;
	}

	public ModelAndView boardView(int bnumber) {
		mav = new ModelAndView();
		/* 
		 * 해야 할 일
		 * 1. 해당글의 조회수 값 증가 (update쿼리)
		 * 2. 해당 글의 내용가져오기(select 쿼리)
		 */
		
		bdao.boardHits(bnumber);
		
		BoardDTO board = bdao.boardView(bnumber);
		mav.addObject("board", board);
		mav.setViewName("boardview");
		
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
