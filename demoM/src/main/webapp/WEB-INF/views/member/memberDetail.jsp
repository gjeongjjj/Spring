<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** memberDetail **</title>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">

</head>
<body>
	<h2>멤버 디테일</h2>

	<table>
		<c:if test="${!empty requestScope.MemberOne}">
			<tr>
				<th bgcolor="coral" height="50">I D</th>
				<td>${requestScope.MemberOne.id }</td>
			</tr>
			<%-- <tr>
			<th bgcolor="coral" height="50" >PASSWORD</th>
			<td>${requestScope.MemberOne.password }</td>
		</tr> --%>
			<tr>
				<th bgcolor="coral" height="50">NAME</th>
				<td>${requestScope.MemberOne.name }</td>
			</tr>
			<tr>
				<th bgcolor="coral" height="50">AGE</th>
				<td>${requestScope.MemberOne.age }</td>
			</tr>
			<tr>
				<th bgcolor="coral" height="50">INFO</th>
				<td>${requestScope.MemberOne.info }</td>
			</tr>
			<tr>
				<th bgcolor="coral" height="50">POINT</th>
				<td>${requestScope.MemberOne.point }</td>
			</tr>
			<tr>
				<th bgcolor="coral" height="50">BIRTHDAY</th>
				<td>${requestScope.MemberOne.birthday }</td>
			</tr>
			<tr>
				<th bgcolor="coral" height="50">추천인</th>
				<td>${requestScope.MemberOne.rid }</td>
			</tr>

			<tr>
				<th bgcolor="coral">Image</th>
				<td><img alt="myImage"
					src="/resources/uploadImages/${requestScope.MemberOne.uploadfile}"
					width="300" height="400"></td>
			</tr>


		</c:if>
		<c:if test="${empty requestScope.MemberOne }">
			<tr>
				<td colspan="9">
					<h3>로그인이 제대로 안된거 같습니다용</h3>
				</td>
			</tr>
		</c:if>
	</table>

	<c:if test="${!empty requestScope.message}">
	=> ${requestScope.message}
</c:if>

	<h3>
		<a href="/home">HOME</a>
	</h3>
	<h3>
		<a href='javascript:history.go(-1)'>이전으로</a>
	</h3>

</body>
</html>