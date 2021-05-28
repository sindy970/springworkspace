<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	function updatefn() {
		var pwd = document.getElementById('pwd').value;
		var boardpwd = '${update.bpassword}';
		
		console.log(${update.btitle});
		console.log(${update.bcontents});
		
		if(pwd == boardpwd){
			updateform.submit();
		} else {
			alert('비밀번호가 틀립니다!!');
		}
	}
</script>
</head>
<body>
<h2>boardupdate.jsp파일 입니다.</h2>
<form action="updateprocess" method="post" name="updateform">
	 글번호 : <input type="text" name="bnumber" value="${update.bnumber}" readonly="readonly"><br>
	 제목 : <input type="text" name="btitle"  value="${update.btitle}"><br>
	 비밀번호 : <input type="text" name="bpassword" id="pwd"><br>
	 작성자 : <input type="text" name="bwriter" value="${update.bwriter}" readonly="readonly"><br>
	 내용 : <input type="text" name="bcontents  "value="${update.bcontents}"><br>
	<input type="button" value="수정" onclick="updatefn()">
</form>
</body>
</html>