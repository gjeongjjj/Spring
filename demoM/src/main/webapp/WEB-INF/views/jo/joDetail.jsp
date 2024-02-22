<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joDetail **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** joDetail **</h2>

<table>
	<c:if test="${!empty requestScope.joOne }">
		<tr>
			<th bgcolor="LightSlateGray" height="50">Jno</th>
			<td>${requestScope.joOne.jno }</td>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Jname</th>
			<td>${requestScope.joOne.jname }</td>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Captain</th>
			<td>${requestScope.joOne.captain }</td>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Project</th>
			<td>${requestScope.joOne.project}</td>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Slogan</th>
			<td>${requestScope.joOne.slogan}</td>
		</tr>
	</c:if>
	<c:if test="${empty requestScope.joOne }">
		<tr>
			<td colspan="5">
				<h3> 다시 선택해주세요!!!</h3>
			</td>	
		</tr>
	</c:if>
</table>

<c:if test="${!empty requestScope.message}">
	=> ${requestScope.message}
</c:if>
<hr>

<table border="1" style="width: 100%">
	<tr bgcolor="Salmon">
		<th>ID</th>
		<th>Password</th>
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
	</tr>
	<c:if test="${!empty requestScope.joOneList }">
		<c:forEach var="jom" items="${requestScope.joOneList }">
			<tr>
				<td>${jom.id}</td>
				<td>${jom.password}</td>
				<td>${jom.name}</td>
				<td>${jom.age}</td>
				<td>${jom.jno}</td>
				<td>${jom.info}</td>
				<td>${jom.point}</td>
				<td>${jom.birthday}</td>
				<td>${jom.rid}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.joOneList }">
		<tr>
			<td colspan="9">(｡･∀･)ﾉﾞ 멤버가 없습니다.</td>
		</tr>
	</c:if>
</table>

<hr>
&nbsp;<h3><a href="jodetail?jCode=A&jno=${requestScope.joOne.jno }">조 수정</a></h3>&nbsp;
&nbsp;<h3><a href="delete?jno=${requestScope.joOne.jno }">조 삭제</a></h3>&nbsp;

<hr>
<h3><a href="/home">HOME</a></h3>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>

</body>
</html>