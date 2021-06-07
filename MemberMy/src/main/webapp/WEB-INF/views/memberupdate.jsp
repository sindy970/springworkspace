<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function updatefn() {
		var passwordConfirm = document.getElementById('pwd').value;
		
		var password = '${memberupdate.mpassword}';
		
		if(password == passwordConfirm){
			updateform.submit();
		} else {
			alert('비밀번호가 틀립니다.!!');
		}
	}
</script>

</head>
<body>
<h2>memberupdate.jsp페이지 입니다.</h2>
<form action="updateprocess" method="post" name="updateform">
	글번호 : <input type="hidden" name="mnumber">
	아이디 : <input type="text" name="mid" value="${memberupdate.mid}" readonly="readonly"><br>
	비밀번호 : <input type="text" name="mpassword" id="pwd"><br>
	이름 : <input type="text" name="mname" value="${memberupdate.mname}" readonly="readonly"><br>
	전화번호 : <input type="text" name="mphone" value="${memberupdate.mphone}"><br>
	이메일 : <input type="text" name="memail" value="${memberupdate.memail}"><br>
	
	<input type="button" value="수정" onclick="updatefn()">
</form>
</body>
</html>