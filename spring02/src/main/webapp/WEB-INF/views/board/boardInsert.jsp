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
<hr>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>
<hr>
<form action="insertb" method="post">
	<table>
		<tr height="40">
			<th bgcolor="Violet"> <label for="id">ID</label></th>
			<td> <input type="text" name="id" value="${loginID}" size="20" > </td>
		</tr>
		<tr height="40">
			<th bgcolor="Violet"> <label for="title">Title</label></th>
			<td> <input type="text" name="title" id="title"> </td>
		</tr>
		<tr height="40">
			<th bgcolor="Violet"> <label for="ctt">Content</label></th>
			<td> <input type="text" name="ctt" id="ctt"> </td>
		</tr>
		<tr height="40">
			<td></td>
			<td> 
				<input type="submit" value="등록" > 
				<input type="reset" value="초기화" >
			</td>
		</tr>
	</table>
</form>


<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>