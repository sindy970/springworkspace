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
</body>
</html>