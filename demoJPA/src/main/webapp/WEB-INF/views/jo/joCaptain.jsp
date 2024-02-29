<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JList+Captain **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** JList+Captain **</h2>
<hr>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<table border="1" style="width: 100%">
	<tr height="50">
		<th>Jno</th>
		<th>Jname</th>
		<th>Captain</th>
		<th>CaptainName</th>
		<th>Project</th>
		<th>Slogan</th>
	</tr>
	<c:if test="${!empty requestScope.joCaptain }">
		<c:forEach var="jo" items="${requestScope.joCaptain }">
			<tr height="40">
				<td> <a href="jodetail?jno=${ jo.jno}&jCode=P"> ${jo.jno }</a> </td>
				<td>${jo.jname }</td>
				<td>${jo.captain }</td>
				<td>${jo.cname }</td>
				<td>${jo.project }</td>
				<td>${jo.slogan }</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.joCaptain }">
		<tr height="50">
			<td colspan="6"> ( ￣^￣) 출력 자료가 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<hr>

<!-- 
여기까지 조 리스트. 
아래에 조 수정 삭제 애들 있어야 함. 
 -->
 &nbsp;<h3><a href="/jo/joInsert">조 등록</a></h3>&nbsp;
 

<hr>
&nbsp;<h3><a href="/home">HOME</a></h3>&nbsp;
&nbsp;<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>&nbsp;


</body>
</html>