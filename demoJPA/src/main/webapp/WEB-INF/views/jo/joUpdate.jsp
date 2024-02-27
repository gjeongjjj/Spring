<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joUpdate **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** joUpdate **</h2>

<form action="update" method="post">
<table>
	<tr height="40">
		<td> <label for="jno">JNO</label></td>
		<td> <input type="number" name="jno" id="jno" value="${requestScope.joOne.jno }" readonly > </td>
	</tr>
	<tr height="40">
		<td> <label for="jname">JNAME</label></td>
		<td> <input type="text" name="jname" id="jname" value="${requestScope.joOne.jname }" > </td>
	</tr>
	<tr height="40">
		<td> <label for="captain">CAPTAIN</label></td>
		<td> <input type="text" name="captain" id="captain" value="${requestScope.joOne.captain }" > </td>
	</tr>
	<tr height="40">
		<td > <label for="project">PROJECT</label></td>
		<td> <input type="text" name="project" id="project" value="${requestScope.joOne.project }" > </td>
	</tr>
	<tr height="40">
		<td > <label for="slogan">SLOGAN</label></td>
		<td> <input type="text" name="slogan" id="slogan" value="${requestScope.joOne.slogan }" > </td>
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

<c:if test="${!empty requestScope.message}">
	=> ${requestScope.message}
</c:if>

<hr>
<h3><a href="/home">HOME</a></h3>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>

</body>
</html>