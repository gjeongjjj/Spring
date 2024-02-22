<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >

</head>
<body>
<h2>** Web02_MVC02 MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<table border="1" style="width: 100%">
	<tr bgcolor="DeepSkyBlue">
		<th>ID</th>
		<!-- <th>Password</th> -->
		<th>Name</th>
		<th>Age</th>
		<th>Jno</th>
		<th>Info</th>
		<th>Point</th>
		<th>Birthday</th>
		<th>추천인</th>
		<th>Image</th>
	</tr>
	<c:if test="${!empty requestScope.banana }">
		<!-- 있으면 출력 없으면 안출력 -->
		<c:forEach var="ba" items="${requestScope.banana}" >
			<!-- requestScope.banana 리스트 안에 있는걸 ba에 넣어서 하나하나 읽을거임 -->
			<tr>
				<!-- jstl 표현식은 getter포함되어 있어서 굳이 getter안써도 됨.  -->
				<td>${ba.id}</td>
				<%-- <td>${ba.password}</td> --%>
				<td>${ba.name}</td>
				<td>${ba.age}</td>
				<td>${ba.jno}</td>
				<td>${ba.info}</td>
				<td>${ba.point}</td>
				<td>${ba.birthday}</td>
				<td>${ba.rid}</td>
				<td> <img alt="myImage" src="/resources/uploadImages/${ba.uploadfile}"
						  width="50" height="70"> </td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.banana }">
		<tr>
			<td colspan="9"> ( ￣^￣) 출력 자료가 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<hr>

&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>