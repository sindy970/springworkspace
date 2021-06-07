<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>memberview.jsp페이지 입니다.</h2>
<h2>memberview.jsp 페이지 입니다.</h2>
아이디 : ${memberdto.mid}<br>
비밀번호 : ${memberdto.mpassword}<br>
이름 : ${memberdto.mname}<br>
전화번호 : ${memberdto.mphone}<br>
이메일 : ${memberdto.memail}<br>
첨부파일 : ${memberdto.mprofilename}<br> 
이미지 :
	<img src="resources/upload/${memberdto.mprofilename}" height="200" width="200">
	<br>
<a href="./">홈으로</a>
<a href="memberlist">리스트로 돌아가기</a>

</body>
</html>