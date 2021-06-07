<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table,th,tr,td{
		border: 1px solid black;
		border-collapse: collapse;
	}
</style>
</head>
<body>
	<h2>boardlist.jsp페이지 입니다.</h2>
	<table>
		<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>제목</th>
		</tr>
		
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.bnumber}</td>
				<td>${board.bwriter}</td>
				<td><a href="boardview?bnumber=${board.bnumber}">${board.btitle}</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="writepage">글쓰기 화면</a>
</body>
</html>