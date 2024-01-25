<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" 
		href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>
<h2>** Hello Spring_MVC02 !!!    </h2>
<P>* Home Time : ${serverTime}. </P>
<hr>

<c:if test="${!empty sessionScope.loginName }">
	${sessionScope.loginName } 님 안녕하세요. <br>
</c:if>
<c:if test="${empty sessionScope.loginID }">
	로그인 후 이용하세요 ~~ <br>
</c:if>
<c:if test="${!empty requestScope.message}">
	<hr> => ${requestScope.message } <br>
</c:if>
<br><br><br>
<img alt="mainImage" src="/spring02/resources/images/mermaid.jpg" width="400" height="250" >
<img alt="mainImage" src="/spring02/resources/images/mermaid2.jpg" width="400" height="250" >
<img alt="mainImage" src="/spring02/resources/images/mermaid3.jpg" width="400" height="250" >
<br><br><br>
<hr>

<!-- Login 전 -->
<c:if test="${empty sessionScope.loginID }">
	&nbsp; <a href="member/loginForm">LoginF</a>
	&nbsp; <a href="member/joinForm">JoinF</a>
</c:if>

<!-- Login 후 -->
<c:if test="${!empty sessionScope.loginID }">
	&nbsp; <a href="member/logout">Logout</a> &nbsp; 
	&nbsp; <a href="member/detail?jCode=D">내 정보</a> &nbsp; 
	&nbsp; <a href="member/detail?jCode=U">내 정보 수정</a> &nbsp; 
	&nbsp; <a href="member/delete">탈퇴</a> &nbsp; 
</c:if>
<br> <hr>
&nbsp;<a href="member/memberList">MList</a>&nbsp; 

</body>
</html>
