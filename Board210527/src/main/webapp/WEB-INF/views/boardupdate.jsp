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
			alert('��й�ȣ�� Ʋ���ϴ�!!');
		}
	}
</script>
</head>
<body>
<h2>boardupdate.jsp���� �Դϴ�.</h2>
<form action="updateprocess" method="post" name="updateform">
	 �۹�ȣ : <input type="text" name="bnumber" value="${update.bnumber}" readonly="readonly"><br>
	 ���� : <input type="text" name="btitle"  value="${update.btitle}"><br>
	 ��й�ȣ : <input type="text" name="bpassword" id="pwd"><br>
	 �ۼ��� : <input type="text" name="bwriter" value="${update.bwriter}" readonly="readonly"><br>
	 ���� : <input type="text" name="bcontents  "value="${update.bcontents}"><br>
	<input type="button" value="����" onclick="updatefn()">
</form>
</body>
</html>