<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script>

	function deletefn(mnumber) {
		location.href="memberdelete?mnumber=" +mnumber;
	}

	function memberviewAjax(mnumber) {
		
		$.ajax({
			type:'post',
			url: 'memberviewajax',
			data: {'mnumber':mnumber},
			dataType: 'json',
			success: function(memberdto){
				var output = "<table>";
				output += "<tr><th>아이디</th> <th>비밀번호</th> <th>이름</th> <th>전화번호</th> <th>이메일</th></tr>";
				output += "<tr>";
				output += "<td>"+memberdto.mid+"</td>";
				output += "<td>"+memberdto.mpassword+"</td>";
				output += "<td>"+memberdto.mname+"</td>";
				output += "<td>"+memberdto.mphone+"</td>";
				output += "<td>"+memberdto.memail+"</td>";
				output += "</tr>";
				output += "</table>";
				
				document.getElementById('memberviewdiv').innerHTML = output; 
			}, 
			error: function(){
				console.log('문제발생');
			}
		});
	}
	
	function logout() {
		location.href = "logout";
	}
	
	function update(mnumber) {
		location.href = "memberupdate?mnumber=" +mnumber;
		}
	 
	</script>

	<style type="text/css">

		table,tr,td,th{
			border: 1px black solid;
			border-collapse: collapse;
		}
		
		div{
			margin-top: 100px
		}

	</style>

</head>
<body>
<h2>memberlist.jsp페이지 입니다.</h2>
<p>${sessionScope.loginId}님 환영합니다.</p>
<table>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>프로필사진</th>
				<th>정보수정</th>
				<th>상세조회</th>
				<th>조회(ajax)</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="member" items="${memberList}">
				<tr>
					<td>${member.mid}</td>
					<td>${member.mpassword}</td>
					<td>${member.mname}</td>
					<td>${member.mphone}</td>
					<td>${member.memail}<br></td>
					<td><img src="resources/upload/${member.mprofilename}" height="200" width="200"></td>
					<td><button onclick="update('${member.mnumber}')">정보수정</button></td>
					<td><a href="memberview?mnumber=${member.mnumber}">상세조회</a></td>
					<td><button onclick="memberviewAjax('${member.mnumber}')">조회(ajax)</button></td>
					<td><button onclick="deletefn('${member.mnumber}')">삭제</button></td>
				</tr>
			</c:forEach>
		</table>
	<button onclick="logout()">로그아웃</button>
	<a href="membermyMain">메인으로</a>
		
		<div id="memberviewdiv">	</div>
		
</body>
</html>