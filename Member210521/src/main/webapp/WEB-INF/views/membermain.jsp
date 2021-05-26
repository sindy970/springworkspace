<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	/*
	 * 로그아웃 버튼을 클릭하면 아래 logout함수가 호출되다.
	 */
	function logout() {
		//아래내용은 logout 주소를 요청하는 내용이다.
		//location.href는 주소이동 하기 위해 사용되는 함수이다.
		location.href = "logout";
	}
	/*
		회원정보 수정버튼을 클릭하면 아래 update함수가 호출된다.
			회원계정 정보중에서 수정 가능한 정보와 불가능한 정보를 나눠서 생각해줘야 한다.
	 */
	function update() {
		location.href = "memberupdate";
	}
</script>
</head>
<body>
	<h2>membermain.jsp파일이다.</h2>

	<!-- 세션에 저장한 값 출력 -->
	로그인아이디 : ${sessionScope.loginMember}<br>
	
	
	



	<h2>${sessionScope.loginMember}님반갑습니다!</h2>

	<!-- 관리자(admin)으로 로그인 했을 때만 회원목록 링크 노출(출력) 
	로그인아이디가 admin이면 회원목록 링크가 보이고 그렇지 않으면 보이지 않음-->
	<!-- if문 이용
			조건식==test 에 넣어주면 된다. -->
	<c:if test="${sessionScope.loginMember eq 'admin'}">
		<a href="listpage">회원목록조회(관리자 전용)</a>
		<h2>로그인 아이디가 admin일때만 보입니다.</h2>
	</c:if>
	<!-- 권한을 분리하여 볼 수 있는 페이지를 나눌 수 있다. -->

	<h2>여기는 누구나 보입니다.</h2>

	<!-- 로그아웃 버튼 생성 -->
	<button onclick="logout()">로그아웃</button>

	<!-- 회원정보수정 절차
		1단계 : 정보수정 버튼을 클릭하면 DB로부터 해당로그인 계정의 정보를 가져와서 memberupdate.jsp에 출력
		2단계 : memberupdate.jsp에서 이메일주소를 수정하고 수정한 내용을 DB에 반영 후 다시 membermain.jsp로 돌아옴.-->
	<button onclick="update()">회원정보수정</button>

	<a href="./">홈으로</a>

</body>
</html>