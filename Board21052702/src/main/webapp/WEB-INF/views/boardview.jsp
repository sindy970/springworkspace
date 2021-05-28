<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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

<a href="boardlist">목록으로 돌아가기</a>

<!-- 수정버튼 만들고 수정기능 구현하기 -->
<a href="boardupdate?bnumber=${board.bnumber}">수정</a>
<!-- 위의 수정링크를 클릭하면 controller->service->dao->db를 거쳐서 데이터를 가지고 
	boardupdate.jsp를 출력한다. 그리고 boardupdate.jsp에서 수정할 내용을 입력받고 db에 update 쿼리를 수행해야 함. -->
</body>
</html>