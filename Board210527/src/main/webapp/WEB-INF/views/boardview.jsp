<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	table,tr,th,td {
		border: 1px black solid;
		border-collapse: collapse; 
	}
</style>

<script type="text/javascript">
	function update() {
		console.log("update�������Դϴ�.")
		location.href = "boardupdate?bnumber=" + ${bnum.bnumber};
	}
</script>
</head>
<body>
	<h2>boardview.jsp������ �Դϴ�.</h2>
	<table>
<tr>
	<th>�۹�ȣ</th>
	<th>������</th>
	<th>�ۺ�й�ȣ</th>
	<th>�ۼ���</th>
	<th>����</th>
	<th>�ۼ�����</th>
	<th>��ȸ��</th>
</tr>
<tr>
	<td>${bnum.bnumber}</td>
	<td>${bnum.btitle}</td>
	<td>${bnum.bpassword}</td>
	<td>${bnum.bwriter}</td>
	<td>${bnum.bcontents}</td>
	<td>${bnum.bdate}</td>
	<td>${bnum.bhits}</td>
</tr>
</table>
<a href="./">Ȩ����</a>
<a href="boardlist">������� ���ư���</a>

<!-- ������ư ����� ������� �����ϱ� -->
<button onclick="update()">�����ϱ�</button>
</body>
</html>