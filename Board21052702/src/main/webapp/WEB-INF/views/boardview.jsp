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
		var pwd = prompt("비밀번호를 입력하세요");
		var bpassword = '${board.bpassword}';

		if (pwd == bpassword) {
			location.href = 'boarddelete?bnumber=' + $
			{
				board.bnumber
			}
			;
		} else {
			alert('비밀번호 불일치');
		}
	}
</script>

</head>
<body>
	<h2>boardview.jsp페이지 입니다.</h2>
	글번호 : ${board.bnumber}
	<br> 제목 : ${board.btitle}
	<br> 비밀번호 : ${board.bpassword}
	<br> 작성자 : ${board.bwriter}
	<br> 내용 : ${board.bcontents}
	<br> 작성날짜 : ${board.bdate}
	<br> 조회수 : ${board.bhits}
	<br> 첨부파일 : ${board.bfilename}
	<br> 이미지 :
	<img src="resources/upload/${board.bfilename}" height="200" width="200">
	<br>

	<a href="boardlist">목록으로 돌아가기</a>

	<!-- 수정버튼 만들고 수정기능 구현하기 -->
	<a href="boardupdate?bnumber=${board.bnumber}">수정</a>
	<br>
	<button onclick="update()">수정버튼</button>
	<br>

	<button onclick="boardDelete()">삭제</button>
	<br>

	<a href="paging?page=${page}">페이징 목록으로 돌아가기</a>

	<!-- 댓글 등록부분 -->
	<div id="comment-write">
		작성자 : <input type="text" id="cwriter"><br> 내용 : <input
			type="text" id="ccontents"><br>
		<button id="cwrite-btn">댓글등록</button>
	</div>

	<!-- 댓글 목록 출력 부분 -->
	<div id="comment-list">
		<table border="1">
			<tr>
				<th>작성자</th>
				<th>내용</th>
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
						output += "<tr><th>작성자</th>";
						output += "<th>내용</th></tr>";
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
						console.log('문제있음.');
					}
				});
			});
		});
	</script>



























</body>
</html>