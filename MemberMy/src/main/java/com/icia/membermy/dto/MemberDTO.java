package com.icia.membermy.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {
	
	private int mnumber;
	private String mid;
	private String mpassword;
	private String mname;
	private String mphone;
	private String memail;
	
	private MultipartFile mprofile;
	private String mprofilename;
	
}
