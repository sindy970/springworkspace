package com.icia.board2.dto;

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
}
