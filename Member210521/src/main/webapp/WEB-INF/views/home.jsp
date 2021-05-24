<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<h2>home.jsp파일이다.</h2>

	<!-- 단순 페이지 이동을 위한 링크 -->
	<a href="joinpage">회원가입 페이지로 이동</a>
	<br>
	<a href="loginpage">로그인 페이지로 이동</a>
	<br>
	<!-- DB에서 정보를 가져와서 memberlist로 출력해주기 위한 링크 -->
	<a href="listpage">목록출력 페이지로 이동</a>
	<br>

</body>
</html>
