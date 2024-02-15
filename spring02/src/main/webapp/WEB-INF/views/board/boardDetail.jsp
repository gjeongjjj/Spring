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
			<th>Seq</th>
			<td>${requestScope.boardOne.seq }</td>
		</tr>
		<tr height="50">
			<th>Title</th>
			<td>${requestScope.boardOne.title }</td>
		</tr>
		<tr height="50">
			<th>I D</th>
			<td>${requestScope.boardOne.id }</td>
		</tr>
		<tr height="150">
			<th>Content</th>
			<td>${requestScope.boardOne.content }</td>
		</tr>
		<tr height="50">
			<th>RegDate</th>
			<td>${requestScope.boardOne.regdate }</td>
		</tr>
		<tr height="50">
			<th>조회수</th>
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

<!-- 로그인 한 경우에는 새 글 등록 -->
<c:if test="${!empty sessionScope.loginID }">
	&nbsp;<a href="boardInsert">새글 등록</a>&nbsp;
	<!-- 댓글 등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
		 서버로 보내주어야 함 ( 쿼리스트링으로 작성 )) -->
	&nbsp;<a href="replyInsert?root=${boardOne.root}&step=${boardOne.step}&indent=${boardOne.indent}">
					답글 등록</a>&nbsp;
</c:if>

<!-- 로그인 id와 글쓴이 id가 도일하면 수정, 삭제 가능.  -->
<c:if test="${loginID == requestScope.boardOne.id }">
	&nbsp;<a href="detail?jCode=U&seq=${requestScope.boardOne.seq }">글 수정</a>&nbsp;
	&nbsp;<a href="delete?seq=${boardOne.seq}&root=${boardOne.root}&title=${boardOne.title}">글 삭제</a>&nbsp;
	<hr>
</c:if>

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>