<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function updatefn() {
		//사용자가 입력한 비밀번호와 DB에서 가져온 비밀번호 일치여부를 판단해서 일치하면 form태그 전송 일치하지 않으면 alert출력
		
		//사용자 입력한 비밀번호 값
		var passwordConfirm = document.getElementById('pwd').value;
		
		//DB에서 가져온 비밀번호 값
		var password = '${member123.mpassword}';
		
		if(password == passwordConfirm){
			//form태그의 name이름.submit을 하게 되면 form태그의 값 전체를 보낸다는 의미이다.
			updateform.submit();
		} else {
			alert('비밀번호가 틀립니다.!!');
		}
	}
</script>
</head>
<body>
<h2>memberupdate.jsp파일이다.</h2>

<!-- 비밀번호 입력란은 비워놓고 비밀번호를 사용자로부터 입력받아 db에 저장된 정보와 일치하면 수정처리 진행 일치하이 않으면 현재페이지에 머무름 -->

<form action="updateprocess" method="post" name="updateform">
	아이디 : <input type="text" name="mid" value="${member123.mid}" readonly="readonly"><br>
	비밀번호 : <input type="text" name="mpassword" id="pwd"><br>
	이름 : <input type="text" name="mname" value="${member123.mname}" readonly="readonly"><br>
	이메일 : <input type="text" name="memail" value="${member123.memail}"><br>
	
	<input type="button" value="수정" onclick="updatefn()">
	<!-- type을 submit으로 하거나 button태그를 클릭하면 form태그 내용이 바로 전송됨 
			하지만 type을 button으로 하면 js함수를 호출하고 싶을 때 값을 보낼 수 있다. -->
</form>
</body>
</html>