<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardList **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >
 
</head>
<body>
<h2>** Spring MVC2 BoardList **</h2>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<table border="1" style="width: 100%">
	<tr>
		<th>Seq</th>
		<th>Title</th>
		<th>I D</th>
		<th>RegDate</th>
		<th>조회수</th>
	</tr>
	<c:if test="${!empty requestScope.boardAll}">
		<!-- 있으면 출력 없으면 안출력 -->
		<c:forEach var="bo" items="${requestScope.boardAll}" >
			<!-- requestScope.banana 리스트 안에 있는걸 ba에 넣어서 하나하나 읽을거임 -->
			<tr>
				<!-- jstl 표현식은 getter포함되어 있어서 굳이 getter안써도 됨.  -->
				<td>${bo.seq}</td>
				<td>
					<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가 -->
					<c:if test="${bo.indent > 0 }">
						<c:forEach begin="1" end="${bo.indent }" >
							<span>&nbsp;&nbsp;</span>
						</c:forEach>
						<span style="color: coral;">re:</span>
					</c:if>
					<c:if test="${!empty loginID }">
						<a href="detail?jCode=D&seq=${bo.seq}">${bo.title}</a>
					</c:if>
					<c:if test="${empty loginID }">
						${bo.title}
					</c:if>
				</td>
				<td>${bo.id}</td>
				<td>${bo.regdate}</td>
				<td>${bo.cnt}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.boardAll }">
		<tr>
			<td colspan="5"> ( ￣^￣) 출력 자료가 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<hr>

<c:if test="${!empty loginID }">
	&nbsp; <a href="boardInsert">글 등록하기 </a>&nbsp;
</c:if>


<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>