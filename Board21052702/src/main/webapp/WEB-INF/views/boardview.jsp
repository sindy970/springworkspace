<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">

	function update() {
		location.href='boardupdate?bnumber='+${board.bnumber};
	}
	
	function boardDelete() {
		var pwd = prompt("비밀번호를 입력하세요");
		var bpassword = '${board.bpassword}';
		
		if(pwd == bpassword){
		location.href='boarddelete?bnumber='+${board.bnumber};			
		} else {
			alert('비밀번호 불일치');
		}
	}
	
</script>

</head>
<body>
<h2>boardview.jsp페이지 입니다.</h2>
글번호 : ${board.bnumber}<br>
제목 : ${board.btitle}<br>
비밀번호 : ${board.bpassword}<br>
작성자 : ${board.bwriter}<br>
내용 : ${board.bcontents}<br>
작성날짜 : ${board.bdate}<br>
조회수 : ${board.bhits}<br>
첨부파일 : ${board.bfilename}<br>
이미지 : <img src="resources/upload/${board.bfilename}" height="200" width="200"><br>

<a href="boardlist">목록으로 돌아가기</a>

<!-- 수정버튼 만들고 수정기능 구현하기 -->
<a href="boardupdate?bnumber=${board.bnumber}">수정</a><br>
<button onclick="update()">수정버튼</button><br>

<button onclick="boardDelete()">삭제</button><br>

<a href="paging?page=${page}">페이징 목록으로 돌아가기</a>

</body>
</html>