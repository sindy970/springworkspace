package com.icia.membermy.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membermy.dto.BoardDTO;
import com.icia.membermy.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	private ModelAndView mav;
	
	@RequestMapping(value="/membermyMain")
	public String membermyMain() {
		return "membermyMain";
	}
	
	//글쓰기 관련 주소처리
	@RequestMapping(value="/writepage")
	public String writepage() {
		return "boardWrite";
	}
	
	//파일 첨부 글쓰기 주소 처리
	@RequestMapping(value="/boardwritefile")
	public ModelAndView boardWriteFile(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {
		mav = bs.boardWriteFile(board);
		return mav;
	}
	
	//글목록 관련 주소 처리
	@RequestMapping(value="/boardlist")
	public ModelAndView boardList() {
		mav = bs.boardList();
		return mav;
	}
	
	@RequestMapping(value="/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber, @RequestParam(value="page", required = false, defaultValue = "1") int page) {
		mav = bs.boardView(bnumber,page);
		return mav;
	}
	
	@RequestMapping(value="/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardUpdate(bnumber);
		return mav;
	}
	
	@RequestMapping(value = "/update")
	public ModelAndView updatdProcess(@ModelAttribute BoardDTO board) {
		mav = bs.updateProcess(board);
		return mav;
	}
	
	@RequestMapping(value="/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardDelete(bnumber);
		System.out.println("boarddelete페이지 입니다.");
		return mav;
	}
	
	@RequestMapping(value="/paging")
	public ModelAndView boardPaging(@RequestParam(value="page", required = false, defaultValue = "1") int page) {
		mav = bs.boardPaging(page);
		return mav;
	}
	
	@RequestMapping(value = "/search")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchType, @RequestParam("keyword") String keyword) {
		mav = bs.boardSearch(searchType,keyword);
		return mav;
	}

}
