package com.icia.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dao.BoardDAO;
import com.icia.board.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;
	private int count = 0;

	public ModelAndView boardwriteInsert(BoardDTO bdto) {
		mav = new ModelAndView();
		int writerResult = bdao.boardwriteInsert(bdto);
		
		if(writerResult >0) {
			mav.setViewName("redirect:/boardlist");
			//결과값을 가지고 다른페이지로 가는 것이기 때문에 redirect를 이용하여 주소이동을 해야지 데이터가 같이 전달이 된다.
		} else {
			mav.setViewName("boardwriteInsert");
		}
		
		return mav;
	}

	public ModelAndView boardList(BoardDTO bdto) {
		mav = new ModelAndView();
		List<BoardDTO> bdtoList = bdao.boardList(bdto);
		
		mav.addObject("bdtoList", bdtoList);
		mav.setViewName("boardlist");
		
		return mav;
	}

	public ModelAndView boardView(int bnumber) {
		mav = new ModelAndView();
		
		bdao.boardHits(bnumber);
		
		BoardDTO bnum = bdao.boardView(bnumber);
		
		mav.addObject("bnum", bnum);
		mav.setViewName("boardview");
		
		return mav;
	}



	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO update = bdao.boardUpdate(bnumber);
		
		mav.addObject("update", update);
		mav.setViewName("boardupdate");
		return mav;
	}

	public ModelAndView boardProcess(BoardDTO board) {
		mav = new ModelAndView();
		int check = bdao.boardProcess(board);
		if(check > 0) {
			//수정 성공
			mav.setViewName("redirect:/boardview?bnumber=" + board.getBnumber());
		} else {
			//수정 실패
			mav.setViewName("boardupdate?bnumber="+board.getBnumber());
		}
		return mav;
	}

	
	

}
