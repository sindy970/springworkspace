<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">

	function update() {
		location.href='boardupdate?bnumber='+${board.bnumber};
	}
	
	function boardDelete() {
		var pwd = prompt("��й�ȣ�� �Է��ϼ���");
		var bpassword = '${board.bpassword}';
		
		if(pwd == bpassword){
		location.href='boarddelete?bnumber='+${board.bnumber};			
		} else {
			alert('��й�ȣ ����ġ');
		}
	}
	
</script>

</head>
<body>
<h2>boardview.jsp������ �Դϴ�.</h2>
�۹�ȣ : ${board.bnumber}<br>
���� : ${board.btitle}<br>
��й�ȣ : ${board.bpassword}<br>
�ۼ��� : ${board.bwriter}<br>
���� : ${board.bcontents}<br>
�ۼ���¥ : ${board.bdate}<br>
��ȸ�� : ${board.bhits}<br>
÷������ : ${board.bfilename}<br>
�̹��� : <img src="resources/upload/${board.bfilename}" height="200" width="200"><br>

<a href="boardlist">������� ���ư���</a>

<!-- ������ư ����� ������� �����ϱ� -->
<a href="boardupdate?bnumber=${board.bnumber}">����</a><br>
<button onclick="update()">������ư</button><br>

<button onclick="boardDelete()">����</button><br>

<a href="paging?page=${page}">����¡ ������� ���ư���</a>

</body>
</html>