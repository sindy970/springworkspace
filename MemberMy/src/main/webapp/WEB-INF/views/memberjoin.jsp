<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script type="text/javascript">
function idcheck() {
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
		
	});
}
</script>
</head>
<body>
<h2>memberjoin.jsp페이지 입니다.</h2>
<form action="memberjoin" method="post" enctype="multipart/form-data">
	아이디 : <input type="text" name="mid" id="mid" onblur="idcheck()"><br>
	<span id="checkresult"></span><br>
	비밀번호 : <input type="text" name="mpassword"><br>
	이름 : <input type="text" name="mname"><br>
	전화번호 : <input type="text" name="mphone"><br>
	이메일 :  <input type="text" name="memail"> <br>
	프로필사진 : <input type="file" name="mprofile"><br>
	<input type="submit" value="회원가입">
</form>

</body>
</html>