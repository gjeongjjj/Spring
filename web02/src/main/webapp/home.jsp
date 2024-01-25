<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** home **</title>
</head>
<body>
	<h2>** Web02_MVC02 **</h2>
	
	<c:if test="${!empty requestScope.message}">
		${requestScope.message }
	</c:if>
	 
	 <c:if test="${!empty sessionScope.loginName }">
	 	<h3>${sessionScope.loginName } 님 안녕하세요^^</h3>
	 </c:if>	 
	 <c:if test="${empty sessionScope.loginName }">
	 	<h3>로그인 후 이용하세요</h3>
	 </c:if>


<img alt="" src="/web02/images/jjj.gif" width="300" height="250" >

<hr>

<c:if test="${not empty sessionScope.loginName}">
	&nbsp;<a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web02/mdetail?jCode=U">내 정보 수정</a>&nbsp;
	&nbsp;<a href="/web02/logout">Logout</a><br>
	&nbsp;<a href="/web02/mdelete">탈퇴</a><br>
	
</c:if>
<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web02/member/joinForm.jsp">Join</a>&nbsp;
</c:if>

&nbsp;<a href="/web02/mlist">MList</a>&nbsp; 

</body>
</html>