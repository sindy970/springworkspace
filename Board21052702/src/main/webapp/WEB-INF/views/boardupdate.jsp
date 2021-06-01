<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function update() {
		var pwd = document.getElementById('pwd').value;
		var bpassword = '${boardUpdate.bpassword}';
		
		if(pwd == bpassword){
			updateform.submit();
		} else {
		alert('비밀번호 불일치');
		}
	}
</script>

</head>
<body>
<h2>boardupdate.jsp페이지 입니다.</h2>
<form action="updateprocess" method="post" name="updateform">
	글번호  : <input type="text" name="bnumber" value="${boardUpdate.bnumber}" readonly="readonly"><br>
	제목  : <input type="text" name="btitle" value="${boardUpdate.btitle}"><br>
	작성자 : <input type="text" name="bwriter" value="${boardUpdate.bwriter}" readonly="readonly"><br>
	비밀번호  : <input type="text" name="bpassword" id="pwd"><br>
	내용  : <textarea rows="5" name="bcontents">${boardUpdat.bcontents}</textarea> <br>
	<input type="button" onclick="update()" value="수정" >
</form>
</body>
</html>