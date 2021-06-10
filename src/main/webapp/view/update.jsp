<%@page import="com.ict.edu.DAO"%>
<%@page import="com.ict.edu.VO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	String idx = request.getParameter("idx");
	VO vo = DAO.getInstance().getSelectOne(idx);
	
	pageContext.setAttribute("vo",vo);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{width:600px; margin:100px auto; text-align: center;}
	a{text-decoration: none;}
	table
	{
		width: 600px;
		border-collapse: collapse;
		text-align: center;
	}
	table, th, td
	{
		border: 1px solid black;
		padding: 5px;
		margin: auto;
	}
	.bg{background-color: #99ccff}
	input{ padding : 5px;  }
</style>
<script type="text/javascript">
	function update_ok(f) 
	{
		// 비밀번호 체크
		if (f.pwd.value == "${vo.pwd}") 
		{
			alert("수정하기");
			f.submit();
		}else
		{
			alert("비밀번호 틀림");
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h2>수정 화면</h2>
		<hr>
		<p>[ <a href="list.jsp">게시물 목록으로 이동</a> ]</p>
		<form method="post" action="update_ok.jsp">
			<table>
				<tbody>
					<tr><th class="bg">작성자</th><td><input type="text" name="name" value="${vo.name}"></td></tr>
					<tr><th class="bg">제목</th><td><input type="text" name="subject" value="${vo.subject}"></td></tr>
					<tr><th class="bg">Email</th><td><input type="text" name="email" value="${vo.email}"></td></tr>
					<tr><th class="bg">비밀번호</th><td><input type="password" name="pwd" ></td></tr>
					<tr>
						<td colspan="2">
							<textarea rows="10" cols="50" name="content"></textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="수정" onclick="update_ok(this.form)">
							<input type="reset" value="취소">
							<%-- DB 수정을 위해서 idx를 넘기자 --%>
							<input type="hidden" name="idx" value="${vo.idx}">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>