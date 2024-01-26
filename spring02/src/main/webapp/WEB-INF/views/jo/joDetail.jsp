<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joDetail **</title>
	<link rel="stylesheet" type="text/css" 
		href="/spring02/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** joDetail **</h2>

<table>
	<c:if test="${!empty requestScope.joOne }">
		<tr>
			<th bgcolor="LightSlateGray" height="50">Jno</th>
			<th>${requestScope.joOne.jno }</th>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Jname</th>
			<th>${requestScope.joOne.jname }</th>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Captain</th>
			<th>${requestScope.joOne.captain }</th>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Project</th>
			<th>${requestScope.joOne.project}</th>
		</tr>
		<tr>
			<th bgcolor="LightSlateGray" height="50">Slogan</th>
			<th>${requestScope.joOne.slogan}</th>
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

&nbsp;<h3><a href="jodetail?jCode=A&jno=${requestScope.joOne.jno }">조 수정</a></h3>&nbsp;
&nbsp;<h3><a href="delete?jno=${requestScope.joOne.jno }">조 삭제</a></h3>&nbsp;

<hr>
<h3><a href="/spring02/home">HOME</a></h3>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>

</body>
</html>