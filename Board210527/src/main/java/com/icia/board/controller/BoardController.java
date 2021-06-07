package com.icia.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board.dao.BoardDAO;
import com.icia.board.dto.BoardDTO;
import com.icia.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	private ModelAndView mav;
	
	//글쓰기 관련 주소처리
	@RequestMapping(value="/boardwrite")
	public String boardwrite() {
		System.out.println("boardwrite페이지로 이동");
		return "boardwriteInsert";
	}
	
	@RequestMapping(value="/boardwriteInsert")
	public ModelAndView boardwriterIndert(@ModelAttribute BoardDTO bdto) {
		mav = bs.boardwriteInsert(bdto);
		return mav;
	}
	
	//글목록 관련 주소 처리
	@RequestMapping(value = "/boardlist")
	public ModelAndView boardList(@ModelAttribute BoardDTO bdto) {
		mav = bs.boardList(bdto);
		return mav;
	}
	
	//상세조회 관련 주소 처리
	@RequestMapping(value = "/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardView(bnumber);
		return mav;
	}

	//수정하기와 관련한 주소 처리
	@RequestMapping(value = "/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardUpdate(bnumber);
		return mav;
	}
	
	@RequestMapping(value="/updateprocess")
	public ModelAndView boardprocess(@ModelAttribute BoardDTO board) {
		mav = bs.boardProcess(board);
		return mav;
	}



























}


