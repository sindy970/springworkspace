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

	function deletefn(id) {
		console.log('삭제할아이디'+id);
		location.href="memberdelete?mid=" +id;
	}

	function memberviewAjax(mid) {
		/*
			ajax를 이용하여 id를 컨트롤러로 보냄
			DB에서 해당 ID의 정보를 리턴받고 리턴받은 값을 화면에 출력
		*/
		console.log(mid);
		
		$.ajax({
			type:'post',
			url: 'memberviewajax',
			data: {'mid':mid},
			/*data: {'mid': {'aa':aa, 'bb':bb, 'cc':{'dd:dd'}}} 의 형태로 objeck안에 objeck를 넣는것이 가능하다.*/
			dataType: 'json',
			/*
				데이터타입을 json으로 함으로서 
				{mid: "admin", mpassword: "1234", mname: "관리자", memail: "admin@naver.com"}
				이런 형식으로 데이터가 표현이 된다.
				ajax 자체의 형식이 json(JavaScript Object Notation)의 표현방식이다.
				json자체가 일종의 데이터 형식이다.
			*/
			success: function(result){
				console.log(result);
				console.log(result.mid);
				console.log(result.mpassword);
				console.log(result.mname);
				console.log(result.memail);
				//각각의 필드에 값을 따로 출력하는 것도 가능하다. 
				
				var output = "<table>";
				output += "<tr><th>아이디</th> <th>비밀번호</th> <th>이름</th> <th>이메일</th></tr>";
				output += "<tr>";
				output += "<td>"+result.mid+"</td>";
				output += "<td>"+result.mpassword+"</td>";
				output += "<td>"+result.mname+"</td>";
				output += "<td>"+result.memail+"</td>";
				output += "</tr>";
				output += "</table>";
				
				document.getElementById('memberviewdiv').innerHTML = output; 
				//ajax를 이용한 화면의 전환이나 새로운 페이지로의 이동없이 변경되는 값들을 실시간으로 표현할 수 있다. 
			}, 
			error: function(){
				console.log('문제발생');
			}
		});
	}
</script>
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
				<th>삭제</th>
				<th>삭제(ajax)</th>
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
	<td><button onclick="deletefn('${member.mid}')">삭제</button></td>
	<td><button onclick="memberviewAjax('${member.mid}')">조회(ajax)</button></td>
				</tr>
			</c:forEach>
		</table>

	<!-- ajax로 가져온 상세조회 데이터를 아래div에 출력 -->
	<div id="memberviewdiv">
	
	</div>
</body>
</html>