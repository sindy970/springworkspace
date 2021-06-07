package com.icia.membermy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.membermy.dao.CommentDAO;
import com.icia.membermy.dto.CommentDTO;


@Service
public class CommentService {
	
	@Autowired
	private CommentDAO cdao;

	public List<CommentDTO> commentWrite(CommentDTO comment) {
		
		int writeResult = cdao.commentWrite(comment);
		
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		if(writeResult > 0) {
			commentList = cdao.commentList(comment.getCbnumber());
		} else {
			commentList = null;
		}
		
		return commentList;
	}

}
