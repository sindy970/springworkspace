<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script type="text/javascript">
	function update() {
		location.href = 'boardupdate?bnumber=' + $
		{
			board.bnumber
		}
		;
	}

	function boardDelete() {
		var pwd = prompt("��й�ȣ�� �Է��ϼ���");
		var bpassword = '${board.bpassword}';

		if (pwd == bpassword) {
			location.href = 'boarddelete?bnumber=' + $
			{
				board.bnumber
			}
			;
		} else {
			alert('��й�ȣ ����ġ');
		}
	}
</script>

</head>
<body>
	<h2>boardview.jsp������ �Դϴ�.</h2>
	�۹�ȣ : ${board.bnumber}
	<br> ���� : ${board.btitle}
	<br> ��й�ȣ : ${board.bpassword}
	<br> �ۼ��� : ${board.bwriter}
	<br> ���� : ${board.bcontents}
	<br> �ۼ���¥ : ${board.bdate}
	<br> ��ȸ�� : ${board.bhits}
	<br> ÷������ : ${board.bfilename}
	<br> �̹��� :
	<img src="resources/upload/${board.bfilename}" height="200" width="200">
	<br>

	<a href="boardlist">������� ���ư���</a>

	<!-- ������ư ����� ������� �����ϱ� -->
	<a href="boardupdate?bnumber=${board.bnumber}">����</a>
	<br>
	<button onclick="update()">������ư</button>
	<br>

	<button onclick="boardDelete()">����</button>
	<br>

	<a href="paging?page=${page}">����¡ ������� ���ư���</a>

	<!-- ��� ��Ϻκ� -->
	<div id="comment-write">
		�ۼ��� : <input type="text" id="cwriter"><br> ���� : <input
			type="text" id="ccontents"><br>
		<button id="cwrite-btn">��۵��</button>
	</div>

	<!-- ��� ��� ��� �κ� -->
	<div id="comment-list">
		<table border="1">
			<tr>
				<th>�ۼ���</th>
				<th>����</th>
			</tr>
			<c:forEach var="comment" items="${commentList}">
				<tr>
					<td>${comment.cwriter}</td>
					<td>${comment.ccontents}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#cwrite-btn").click(function() {
				var cwriter = document.getElementById('cwriter').value;
				var ccontents = document.getElementById('ccontents').value;
				var cbnumber = '${board.bnumber}';

				console.log(cwriter);
				console.log(ccontents);
				console.log(cbnumber);

				$.ajax({
					type : 'post',
					url : 'comment/commentwrite',
					data : {
						'cwriter' : cwriter,
						'ccontents' : ccontents,
						'cbnumber' : cbnumber
					},
					dataType : 'json',
					success : function(list) {
						console.log(list);
						var output = "<table border='1'>";
						output += "<tr><th>�ۼ���</th>";
						output += "<th>����</th></tr>";
						for(var i in list){
							output += "<tr>";
							output += "<td>"+list[i].cwriter+"</td>";
							output += "<td>"+list[i].ccontents+"</td>";
							output += "</tr>";
						}
						output += "</table>";
						document.getElementById('comment-list').innerHTML = output;
						document.getElementById('cwriter').value='';
						document.getElementById('ccontents').value='';
					},
					error : function() {
						console.log('��������.');
					}
				});
			});
		});
	</script>



























</body>
</html>