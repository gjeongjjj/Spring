<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board Insert **</title>
	<link rel="stylesheet" type="text/css" 
		href="/spring02/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** Spring MVC2 BoardInsert **</h2>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>
<hr>
<form action="update" method="post">
	<table border="1" style="width: 100%">
		<tr height="50">
			<th bgcolor="Tan">Seq</th>
			<td><input type="number" name="jno" id="jno" value="${requestScope.boardOne.seq }" readonly ></td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">Title</th>
			<td><input type="text" name="title" id="title" ></td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">I D</th>
			<td><input type="text" name="title" id="title" value="${requestScope.boardOne.id }" readonly></td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">Content</th>
			<td><input type="text" name="content" id="content" ></td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">RegDate</th>
			<td>${requestScope.boardOne.regdate }</td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">조회수</th>
			<td>${requestScope.boardOne.cnt }</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="조 정보 수정" > &nbsp; &nbsp; 
				<input type="reset" value="재입력">
			</td>
		</tr>
		
	</table>
</form>



<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>