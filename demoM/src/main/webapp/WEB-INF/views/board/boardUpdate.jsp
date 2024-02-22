<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 Board Insert **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >

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
			<th>Seq</th>
			<td class="gray"><input type="number" name="seq" id="seq" value="${requestScope.boardOne.seq }" readonly ></td>
		</tr>
		<tr height="50">
			<th>Title</th>
			<td><input type="text" name="title" id="title" value="${requestScope.boardOne.title }" ></td>
		</tr>
		<tr height="50">
			<th>I D</th>
			<td class="gray"><input type="text" name="id" id="id" value="${requestScope.boardOne.id }" readonly></td>
		</tr>
		<tr height="50">
			<th>Content</th>
			<td> <textarea rows="10" cols="30" name="content"></textarea> </td>
			<%-- <td><input type="text" name="content" value="${requestScope.boardOne.content }"></td> --%>
		</tr>
		<tr height="50">
			<th>RegDate</th>
			<td>${requestScope.boardOne.regdate }</td>
		</tr>
		<tr height="50">
			<th>조회수</th>
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
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>