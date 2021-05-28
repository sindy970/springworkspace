<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
	<a href="writepage">글쓰기 화면</a>
	<a href="boardlist">글목록</a>
	<!-- 목록 링크를 클릭하면 단순히 boardlist.jsp로 이동하는 것이 아니라 DB에서 데이터를 가져와서 boardlist.jsp에 출력한다. -->
	

</body>
</html>
