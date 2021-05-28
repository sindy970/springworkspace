<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>boardwrite.jsp페이지</h2>
<form action="boardwrite">
	작성자 : <input type="text" name="bwriter"><br>
	비밀번호 : <input type="text" name="bpassword"><br>
	제목 : <input type="text" name="btitle"><br>
	내용 : <textarea name="bcontents" rows="5"></textarea><br>
	<input type="submit" value="작성" >
</form>
</body>
</html>