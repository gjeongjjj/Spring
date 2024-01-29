<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board Detail **</title>
	<link rel="stylesheet" type="text/css" 
		href="/spring02/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** Spring MVC2 BoardDetail **</h2>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<table border="1" style="width: 100%">
	<c:if test="${!empty requestScope.boardOne}">
		<tr height="50">
			<th bgcolor="Tan">Seq</th>
			<td>${requestScope.boardOne.seq }</td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">Title</th>
			<td>${requestScope.boardOne.title }</td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">I D</th>
			<td>${requestScope.boardOne.id }</td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">Content</th>
			<td>${requestScope.boardOne.content }</td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">RegDate</th>
			<td>${requestScope.boardOne.regdate }</td>
		</tr>
		<tr height="50">
			<th bgcolor="Tan">조회수</th>
			<td>${requestScope.boardOne.cnt }</td>
		</tr>
	</c:if>
	<c:if test="${empty requestScope.boardOne }">
		<tr>
			<td colspan="5"> ( ￣^￣) 게시글이 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<hr>

<c:if test="${loginID == requestScope.boardOne.id }">
	&nbsp;<a href="boardUpdate">글 수정</a>&nbsp;
	&nbsp;<a href="/board/delete?seq=${requestScope.boardOne.seq}">글 삭제</a>&nbsp;
	<hr>
</c:if>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>