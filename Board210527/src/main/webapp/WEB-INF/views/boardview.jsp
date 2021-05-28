<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	table,tr,th,td {
		border: 1px black solid;
		border-collapse: collapse; 
	}
</style>
</head>
<body>
	<h2>boardview.jsp페이지 입니다.</h2>
	<table>
<tr>
	<th>글번호</th>
	<th>글제목</th>
	<th>글비밀번호</th>
	<th>작성자</th>
	<th>내용</th>
	<th>작성일자</th>
	<th>조회수</th>
</tr>
<tr>
	<td>${bnum.bnumber}</td>
	<td>${bnum.btitle}</td>
	<td>${bnum.bpassword}</td>
	<td>${bnum.bwriter}</td>
	<td>${bnum.bcontents}</td>
	<td>${bnum.bdate}</td>
	<td>${bnum.bhits}</td>
</tr>
</table>
<a href="./">홈으로</a>
<a href="boardlist">목록으로 돌아가기</a>
</body>
</html>