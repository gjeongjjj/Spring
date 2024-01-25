<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** memberDetail **</title>
</head>
<body>
<h2>멤버 디테일</h2>

<table>
	<c:if test="${!empty requestScope.apple}">
		<tr>
			<th bgcolor="coral" height="50">I D</th>
			<td>${requestScope.apple.id }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >PASSWORD</th>
			<td>${requestScope.apple.password }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >NAME</th>
			<td>${requestScope.apple.name }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >AGE</th>
			<td>${requestScope.apple.age }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >INFO</th>
			<td>${requestScope.apple.info }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >POINT</th>
			<td>${requestScope.apple.point }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >BIRTHDAY</th>
			<td>${requestScope.apple.birthday }</td>
		</tr>
		<tr>
			<th bgcolor="coral" height="50" >추천인</th>
			<td>${requestScope.apple.rid }</td>
		</tr>
	</c:if>
	<c:if test="${empty requestScope.apple }">
		<tr>
			<td colspan="9">
				<h3> 로그인이 제대로 안된거 같습니다용 </h3>
			</td>	
		</tr>
	</c:if>
</table>

<h3><a href="home.jsp">HOME</a></h3>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>

</body>
</html>