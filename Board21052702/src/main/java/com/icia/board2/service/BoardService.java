package com.icia.board2.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.board2.dao.BoardDAO;
import com.icia.board2.dao.CommentDAO;
import com.icia.board2.dto.BoardDTO;
import com.icia.board2.dto.CommentDTO;
import com.icia.board2.dto.PageDTO;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO bdao;
	
	@Autowired
	private CommentDAO cdao;
	
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
	
	public ModelAndView boardWriteFile(BoardDTO board) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		
		//dto에 담긴 파일을 가져 온다.
		MultipartFile bfile = board.getBfile();
		
		//파일이름을 가져온다.(파일이름을 db에 저장하기 위해 사용됨)
		String bfilename = bfile.getOriginalFilename();
		
		//파일명 중복을 피하기 위해 파일이름앞에 현재 시간을 붙인다.
		bfilename = System.currentTimeMillis()+"-"+bfilename;
		System.out.println("boardWriteFile메소드 " + bfilename);
		
		//어느 특정 장소에 파일 저장하기
		String savePath = "C:\\Users\\hsh\\git\\springworkspace\\Board21052702\\src\\main\\webapp\\resources\\upload\\"+bfilename;
		
		//bfile이 비어있지 않다면 savePath에 저장하겠다.
		if(!bfile.isEmpty()) {
			//예외처리를 해줘야 한다. (throws로)
			bfile.transferTo(new File(savePath));
		} 
		//여기까지의 내용은 파일을 저장하는 과정이다.
		
		//파일 이름을 dto에 담는 것
		board.setBfilename(bfilename);
		
		//insert쿼리를 실행하기 위해서 DB에 담는 과정
		bdao.boardWriteFile(board);
		
		mav.setViewName("redirect:/boardlist");
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

	public ModelAndView boardView(int bnumber, int page) {
		mav = new ModelAndView();
		/* 
		 * 해야 할 일
		 * 1. 해당글의 조회수 값 증가 (update쿼리)
		 * 2. 해당 글의 내용가져오기(select 쿼리)
		 */
		
		bdao.boardHits(bnumber);
		
		BoardDTO board = bdao.boardView(bnumber);
		
		//상세보기 후 목록으로 돌아갈때 현재 페이지를 유지하기 위해 page도 같이 가져간다.
		mav.addObject("page", page);
		
		//boardview.jsp를 열 때 DB에 저장된 댓글 리스트도 함께 가져간다.
		//목록에서 상세조회 했을 경우 기존에 있는 댓글을 모두 함께 출력해 주기 위해서 처리하는 코딩부분이다.
		List<CommentDTO> commentList = cdao.commentList(bnumber);
		mav.addObject("commentList", commentList);
		
		mav.addObject("board", board);
		mav.setViewName("boardview");
		
		return mav;
	}
	
	
	public ModelAndView boardUpdate(int bnumber) {
		mav = new ModelAndView();
		BoardDTO board = bdao.boardView(bnumber);
		System.out.println("update처리 페이지");
		mav.addObject("boardUpdate", board);
		mav.setViewName("boardupdate");
		return mav;
	}

	public ModelAndView updateProcess(BoardDTO board) {
		mav = new ModelAndView();
		int updateResult = bdao.updateProcess(board);
		
		if(updateResult > 0) {
			//1. 목록출력
//			mav.setViewName("boardlist"); //boardlist.jsp로 목적지 지정을 한 것이다.
			mav.setViewName("redirect:/boardlist"); //데이터를 같이 보내기 위애서 redirect를 사용하여 controller의 주소값으로 목적지를 지정해 준다.
			//2. 해당 글의 상세화면 출력
			mav.setViewName("redirect:/boardview?bnumber="+board.getBnumber());
		} else {
			
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

	//페이징 처리에 활용할 상수
	private static final int PAGE_LIMIT = 3; //한페이지에 보여질 글의 갯수
	private static final int BLOCK_LIMIT = 3; //한 화면에 보여질 페이지 갯수(밑에서 표지되는 페이지의 갯수 즉 [이전]1,2,3[다음] 의 형식으로 보여진다.)
	
	public ModelAndView boardPaging(int page) {
		mav = new ModelAndView();
		//게시글 갯수 가져오기
		int listCount = bdao.listCount();
		
		//매개변수로 넘겨받은page변수값을 기준으로 현재 보여줘야 하는 게시글의 범위 계산
		int startRow = (page-1)*PAGE_LIMIT + 1;
		int endRow = page*PAGE_LIMIT;
		
		//page=1 일때(1페이지클릭) startRow=1, endRow=3
		//page=2 일때(2페이지클릭) startRow=4, endRow=6
		//page=3 일때(3페이지클릭) startRow=7, endRow=9
		
		//startRow,endRow를 기준으로 boardlist가져오기
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<BoardDTO> boardList = bdao.boardPaging(paging);
		
		
		//필요한 페이지 계산
		int maxPage = (int)(Math.ceil((double)listCount / PAGE_LIMIT)); //최대페이지의 갯수 (math.ceil이라는 함수로 올림처리를 해줘야 한다) math.ceil()이 올림처리를 해주는 함수
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1; 
		// staertPage => 페이지 번호에서 시작할 페이지의 번호 
		//		=> 사용자 눈에 보이는 페이지의 시작페이지를 지정해주는 변수이다.
		int endPage = startPage + BLOCK_LIMIT - 1;
		// endPage => 사용자 눈에 보이는 페이지의 끝페이지를 지정해주는 변수이다.
		
		if(endPage > maxPage) {
			/*
			 * 계산결과 전체 페이지 객수는 4개밖에 없는데 
			 * BLOCK_LIMIT 5라면 endPage는 5의 값을 갖게 된다.
			 * 그러면 5페이지는 클릭을 해봐야 나올 데이터도 없고 필요없는 페이지 번호이다. 따라서 BLOCK_LIMIT이 5라고 하더라도
			 * 하단의 페이지 목록은 1,2,3,4만 노출되면 되기  떄문에 아래와 같은 식을 통해 endPAGE값을 조정해 준다.
			 * 
			 * 즉, BLOCK_LIMIT에 의해서 정해진 페이지보다 실제로 보여지는 페이지의 수가 적으면 실제 이용되는 페이지만 보여주기 위해서 
			 * 		if문을 이용하여 지정해 준 것이다.
			 */
			endPage = maxPage;
		}
		
		//계산된 page관련 값을 PageDTO객체에 담는다.
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
		
		//map을 사용하여 mapper로 전달한다. (매개변수가 한개가 아닌 여러개인 경우에 묶어서 DAO로 보내기 위해서 사용한다.)
		Map<String, String> searchMap = new HashMap<String, String>();
		//Map은 반드시 두가지 타입으로 선언해야 한다. <키값의 데이터 타입, value값의 데이터 타입>으로 분리한다.
		//키 => 어떤데이터를 담아놓은 공간
		//value => 실질적인 데이터
		//put(키값,value값); => 데이터를 저장할 때 사용되는 함수이다.
		searchMap.put("type", searchType);
		searchMap.put("word", keyword);
		
		List<BoardDTO> boardList = bdao.boardSearch(searchMap);
		//파라미터 하나만 DAO로 보낼 수 있기때문에 map을 이용하여 묶어준 것이다.
		
		mav.addObject("boardList",boardList);
		mav.setViewName("boardlist");
		
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
