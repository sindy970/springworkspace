<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">
	function idoverlap() {
		/* 함수의 역할
			- 사용자가 입력한 id값을 백엔드로 보내고 DB에서 중복여부를 확인한 후 겨로가를 가져옴
			- 비동기식으로 처리를 하기위해 ajax를 사용함.
				: ajax(Asynchronous javascript and xml)의 약어
						= 비동기식 처리를 위한 문법(화면과 주소가 바뀌는 것없이 백엔드에 요청을 보내고 결과를 받을 수 있음)
						= jquery문법으로 이용할 예정(ajax를 선언하여 사용하는 형식 자체가 jquery를 사용하는 것이다.)*/
		var inputId = document.getElementById('mid').value;
		var checkResult = document.getElementById('checkresult');
		
		console.log(inputId);
		/* ajax사용 형식 : $.ajax({ 실행할 내용}); 의 혛식으로 사용 된다.*/
		$.ajax({
			type: 'post', // 전송방식(get,post.delete.patch,put)
			url: 'idcheck',// 요청주소(컨트롤러에서 받는 주소)
			data: {'mid': inputId}, //전송할 파라미터(데이터) ==>형식 : data:{'변수이름' : 전송할파라미터의 값이 있는 내가 지정한 변수이름}
			// mid = inputId 의 형태와 같은 의미이다.
			dataType: 'text', //리턴데이터형식(컨트롤러에서 다시 받아올때)
			success: function(abcd){ //성공시 처리할 함수
				console.log('제대로 돌고 있음');
				console.log('리턴값' + abcd);
				if(abcd=="ok"){
					checkResult.style.color='green';
					checkResult.innerHTML ='사용가능한 아이디입니다.';
				} else {
					checkResult.style.color='red';
					checkResult.innerHTML ='이미 사용중인 아이디입니다.';
				}
			},
			error: function(){ //실패시 처리할 함수
				console.log('제대로 안돌고 있음');
			}
			
			// ,(콤마) 로 연결하여 여러개의 방식(옵션)을 지정한다.
			// 검정색으로 표시되는 것들은 ajax에서 지정된 형식이기 때문에 내가 변경 불가능하다.
		});
	}
</script>
</head>
<body>
<!-- 동기식 : 주소,화면이 바뀌면서 DB를 다녀옴
	비동기식 :  주소,화면이 바뀌지 않고도 DB를 다녀옴 즉 사용자 눈에는 변화가 없지만 DB를 다녀온다.-->
	
	<!-- 아이디 or 비밀번호를 체크하는 경우에 비동기식을 많이 사용한다. -->
	<h2>memberjoin.jsp파일이다.</h2>

	<form action="memberjoin" method="post">
		아이디 : <input type="text" name="mid" id="mid" onkeyup="idoverlap()"><br>
		<span id="checkresult"></span><br> 
		비밀번호 : <input	type="text" name="mpassword"><br> 
		이름 : <input type="text" name="mname"><br> 
		이메일 : <input type="text" name="memail"><br> 
		 <input type="submit" value="회원가입">
	</form>

</body>
</html>