<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>memberview.jsp 페이지 입니다.</h2>
아이디 : ${result.mid}<br>
비밀번호 : ${result.mpassword}<br>
이름 : ${result.mname}<br>
이메일 : ${result.memail}<br>

<a href="./">홈으로</a>
<a href="listpage">리스트로 돌아가기</a>
</body>
</html>