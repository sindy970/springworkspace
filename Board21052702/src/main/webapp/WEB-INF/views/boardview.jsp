<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
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

<a href="boardlist">������� ���ư���</a>

<!-- ������ư ����� ������� �����ϱ� -->
<a href="boardupdate?bnumber=${board.bnumber}">����</a>
<!-- ���� ������ũ�� Ŭ���ϸ� controller->service->dao->db�� ���ļ� �����͸� ������ 
	boardupdate.jsp�� ����Ѵ�. �׸��� boardupdate.jsp���� ������ ������ �Է¹ް� db�� update ������ �����ؾ� ��. -->
</body>
</html>