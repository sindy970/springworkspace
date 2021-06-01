package com.icia.board2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.board2.dto.CommentDTO;
import com.icia.board2.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
//주소를 분리하기 위해서 이렇게 사용한다.
	
	@Autowired
	private CommentService cs;
	
	@RequestMapping(value="commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment){
		List<CommentDTO> commentList = cs.commentWrite(comment);
		return commentList;
	}
}
