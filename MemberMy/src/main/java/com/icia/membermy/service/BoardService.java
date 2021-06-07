package com.icia.membermy.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.membermy.dao.BoardDAO;
import com.icia.membermy.dao.CommentDAO;
import com.icia.membermy.dto.BoardDTO;
import com.icia.membermy.dto.CommentDTO;
import com.icia.membermy.dto.PageDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private CommentDAO cdao;
	
	
	private ModelAndView mav;

	public ModelAndView boardWriteFile(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		
		MultipartFile bfile = board.getBfile();
		
		String bfilename = bfile.getOriginalFilename();
		
		bfilename = System.currentTimeMillis()+"-"+bfilename;
		System.out.println("boardWriteFile메소드 " + bfilename);
		
		String savePath = "C:\\Users\\hsh\\git\\springworkspace\\Board21052702\\src\\main\\webapp\\resources\\upload\\"+bfilename;
		
		if(!bfile.isEmpty()) {
			bfile.transferTo(new File(savePath));
		} 
		
		board.setBfilename(bfilename);
		
		bdao.boardWriteFile(board);
		
		mav.setViewName("redirect:/boardlist");
		return mav;
	}

	public ModelAndView boardList() {
		mav = new ModelAndView();
		
		List<BoardDTO> boardList = bdao.boardList();
		
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlist");
		return mav;
	}

	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		
		BoardDTO board = bdao.boardView(bnumber);
		
		mav.addObject("page", page);
		
		List<CommentDTO> commentList = cdao.commentList(bnumber);
		mav.addObject("commentList", commentList);
		
		mav.addObject("board", board);
		mav.setViewName("boardview");
		
		return mav;
	}

	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO board = bdao.boardView(bnumber);
		mav.addObject("boardUpdate", board);
		mav.setViewName("boardupdate");
		return mav;
	}
	

	public ModelAndView updateProcess(BoardDTO board) {
		mav = new ModelAndView();
		int updateResult = bdao.updateProcess(board);
		
		if(updateResult > 0) { 
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		}
			
		return mav;
	}

	public ModelAndView boardDelete(int bnumber) {
		mav = new ModelAndView();
		int deleteResult = bdao.boardDelete(bnumber);
		
		if(deleteResult > 0) {
			mav.setViewName("redirect:/boardlist");
		}
		return mav;
	}

	private static final int PAGE_LIMIT = 3; 
	private static final int BLOCK_LIMIT = 3; 
	
	public ModelAndView boardPaging(int page) {
		mav = new ModelAndView();
		int listCount = bdao.listCount();
		
		int startRow = (page-1)*PAGE_LIMIT + 1;
		int endRow = page*PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = bdao.boardPaging(paging);
		
		
		int maxPage = (int)(Math.ceil((double)listCount / PAGE_LIMIT)); //최대페이지의 갯수 (math.ceil이라는 함수로 올림처리를 해줘야 한다) math.ceil()이 올림처리를 해주는 함수
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1; 
		int endPage = startPage + BLOCK_LIMIT - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("boardList", boardList);
		mav.setViewName("boardlistpaging");
		
		return mav;
	}

	public ModelAndView boardSearch(String searchType, String keyword) {
		mav = new ModelAndView();
		
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("type", searchType);
		searchMap.put("word", keyword);
		
		List<BoardDTO> boardList = bdao.boardSearch(searchMap);
		
		mav.addObject("boardList",boardList);
		mav.setViewName("boardlist");
		
		return mav;
	}

	

}
