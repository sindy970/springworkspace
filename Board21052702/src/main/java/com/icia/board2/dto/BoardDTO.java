package com.icia.board2.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data //getter,setter,toString,생성자를 자동으로 만들어줌
public class BoardDTO {
	//lombok을 설치하여서 가능한 어노테이션이다.
	private int bnumber;
	private String bwriter;
	private String bpassword;
	private String btitle;
	private String bcontents;
	private String bdate;
	private int bhits;
	
	//파일을 담기 위한 필드(boardlist.jsp에서 controller로 전달할때 사용되는 필드이다.)
	private MultipartFile bfile;
	//서버에 경로상에 파일을 저장하는 것이고 데이터베이스에는 파일 이름만 저장을 한다. 나중에는 파일 이름을 가지고 파일을 찾는다.
	
	//파일명을 담기 위한 필드(실제로 데이터베이스에 저장할 때 사용되는 필드)
	private String bfilename;
}
