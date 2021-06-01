package com.icia.board2.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board2.dto.BoardDTO;
import com.icia.board2.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService bs;
	
	private ModelAndView mav;
	
	//글쓰기 관련 주소처리
	@RequestMapping(value="/writepage")
	public String writepage() {
		return "boardwrite";
	}
	
	@RequestMapping(value="/boardwrite")
	public ModelAndView boardWrite(@ModelAttribute BoardDTO board) {
		mav = bs.boardWrite(board);
		return mav;
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
	
	//조회 관련 주소 처리
	@RequestMapping(value="/boardview")
	public ModelAndView boardView(@RequestParam("bnumber") int bnumber, @RequestParam(value="page", required = false, defaultValue = "1") int page) {
		mav = bs.boardView(bnumber,page);
		return mav;
	}
	
	//글수정페이지 주소 처리
	@RequestMapping(value="/boardupdate")
	public ModelAndView boardUpdate(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardUpdate(bnumber);
		System.out.println("update 페이지");
		return mav;
	}
	
	//글수정 데이터 처리 과정 주소 처리
	@RequestMapping(value = "/updateprocess")
	public ModelAndView updatdProcess(@ModelAttribute BoardDTO board) {
		mav = bs.updateProcess(board);
		return mav;
	}
	
	//글삭제 주소 처리
	@RequestMapping(value="/boarddelete")
	public ModelAndView boardDelete(@RequestParam("bnumber") int bnumber) {
		mav = bs.boardDelete(bnumber);
		System.out.println("boarddelete페이지 입니다.");
		return mav;
	}
	
	//페이징 처리 관련 주소 처리
	@RequestMapping(value="/paging")
	public ModelAndView boardPaging(@RequestParam(value="page", required = false, defaultValue = "1") int page) {
		//@RequestParam(value="page", required = false, defaultValue = "1")의 의미
		// value = 페이징퍼리를 할때 계속 페이지 값을 처리를 해줘야 한다. 그래서 
		// required = 페이지 값이 굳이 넘어오지 않아도 오류가 발생하지 않는다. 즉 필수 전달내용이 아니라는 말이다. 왜냐하면 기본값으로 1을 주기 때문에 특정 값이 넘어오지 않아도 괜찮다.
		// defaultValue = 처름 paging주소에 접근하는 경우에 기본으로 보여줄 페이지 정보이다. 즉 기본 페이지 값 지정을 위해서 사용됨
		mav = bs.boardPaging(page);
		return mav;
	}
	
	//검색창 관련 주소 처리
	@RequestMapping(value = "/search")
	public ModelAndView boardSearch(@RequestParam("searchtype") String searchType, @RequestParam("keyword") String keyword) {
		mav = bs.boardSearch(searchType,keyword);
		return mav;
	}
} 
