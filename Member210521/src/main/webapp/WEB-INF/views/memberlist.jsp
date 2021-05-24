<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, tr, td, th {
	border: 1px black solid;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h2>memberlist.jsp 페이지 입니다.</h2>
		<table>
			<tr>
			<!-- th태그를 이용하여 속성이름을 표시해 주면 보기가 더 쉽다. -->
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>상세조회</th>
				<!-- 조회링크를 클릭하면 memberview.jsp에 해당회원의 정보만 출력 -->
			</tr>
			<c:forEach var="member" items="${mdtoList}">
				<tr>
					<td>${member.mid}</td>
					<td>${member.mpassword}</td>
					<td>${member.mname}</td>
					<td>${member.memail}<br></td>
	<td><a href="memberview?mid=${member.mid}">조회</a></td>
	<!-- memberview라는 주소를 요청하면서 mid파라미터에 값을 담아서 간다. -->
				</tr>
			</c:forEach>
		</table>

</body>
</html>