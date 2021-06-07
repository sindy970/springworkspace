package com.icia.membermy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.membermy.dto.CommentDTO;
import com.icia.membermy.service.CommentService;


@Controller
@RequestMapping("/comment/*")
public class CommentController {
	
	@Autowired
	private CommentService cs;
	
	@RequestMapping(value="commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment){
		List<CommentDTO> commentList = cs.commentWrite(comment);
		return commentList;
	}
}
