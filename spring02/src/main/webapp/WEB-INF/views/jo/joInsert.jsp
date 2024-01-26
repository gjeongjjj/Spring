<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** joInsert **</title>
	<link rel="stylesheet" type="text/css" 
	href="/spring02/resources/myLib/myStyle.css" >
	
</head>
<body>
<h2>** joInsert **</h2>

<form action="insert" method="post">
	<table>
		<tr height="40">
			<td bgcolor="CadetBlue"> <label for="jno">JNO</label></td>
			<td> <input type="number" name="jno" id="jno" placeholder="숫자만 가능합니다" > </td>
		</tr>
		<tr height="40">
			<td bgcolor="CadetBlue"> <label for="jname">JNAME</label></td>
			<td> <input type="text" name="jname" id="jname" > </td>
		</tr>
		<tr height="40">
			<td bgcolor="CadetBlue"> <label for="captain">CAPTAIN</label></td>
			<td> <input type="text" name="captain" id="captain" > </td>
		</tr>
		<tr height="40">
			<td bgcolor="CadetBlue"> <label for="project">PROJECT</label></td>
			<td> <input type="text" name="project" id="project" > </td>
		</tr>
		<tr height="40">
			<td bgcolor="CadetBlue"> <label for="slogan">SLOGAN</label></td>
			<td> <input type="text" name="slogan" id="slogan" > </td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="submit" value="조 가입" > &nbsp; &nbsp; 
				<input type="reset" value="재입력">
			</td>
		</tr>
	</table>
</form>

<hr>

<c:if test="${!empty requestScope.message }">
	=> ${requestScope.message }
</c:if>

<hr>

&nbsp; <a href="/spring02/home">Home</a>&nbsp; 
&nbsp; <a href="javascript:history.go(-1)">BACK</a>&nbsp; 


</body>
</html>