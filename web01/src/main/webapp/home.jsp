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
	<h2>** Dynamic Web Project **</h2>
	 
	 <c:choose>
		<c:when test="${!empty sessionScope.loginName}">
			<span>${sessionScope.loginName} 님 안녕하세요!</span>
			<br>
		</c:when>
		<c:otherwise>
			<span>로그인 해라.</span>
		</c:otherwise>
	 </c:choose>
	 
	 <c:if test="${!empty sessionScope.loginName }">
	 	<h3>${sessionScope.loginName } 님 안녕하세요^^</h3>
	 </c:if>	 
	 <c:if test="${empty sessionScope.loginName }">
	 	<h3>로그인 후 이용하세요</h3>
	 </c:if>
	 
	
<%-- 	<% 
		if (session.getAttribute("loginName") != null){ %> 
			<h3>
			<%= session.getAttribute("loginName")%>님 안녕하세요~~ </h3>
		<%
		} else {%>
			<h3>로그인 해라</h3>
<%		} %> --%>

	<hr>
	<form action="getpost" method= "get">
		<input type = "text" name = "id" value = "banana"> &nbsp;
		<input type = "text" name = "name" value = "바나나">
		<input type="text" name="password" size="10">
		
		<input type = "submit" value = "Test">
	</form>
<hr>
<!-- ** 경로표기 
	=> 절대경로 : /로 시작, 프로젝트명부터 전체경로표기 
		-> 예시 /web01/images/legsgo.png
		-> webapp 폴더는 생략됨.
	
	=> 상대경로 : 현재위치에서 시작, /로 시작하면 안됨.
		-> ./ : 현재위치 의미, ../ : 1단계 상위
		-> "./images/letsgo.png", images/letsgo.png" 동일
 -->

<img alt="" src="/web01/images/jjj.gif" width="300" height="250" >
<img alt="" src="/web01/images/jjj3.gif" width="300" height="250" >
<img alt="" src="/web01/images/jjj2.gif" width="300" height="250" >
<hr>
<!-- a 태그 -->
<!-- &nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
&nbsp;<a href="/web01/logout">Logout</a>&nbsp;  <br> -->

<hr>
<c:if test="${not empty sessionScope.loginName}">
	&nbsp;<a href="/web01/detail">MyInfo</a>&nbsp;
	&nbsp;<a href="/web01/logout">Logout</a><br>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="#">Join</a><br>
</c:if>

&nbsp;<a href="/web01/hello">Hello</a>&nbsp; 
&nbsp;<a href="/web01/list">M01List</a>&nbsp;
&nbsp;<a href="/web01/life">LifeCycle</a>&nbsp;<br>

&nbsp;<a href="/web01/servletTestForm/form01_Adder.html">Adder</a>&nbsp; 
&nbsp;<a href="/web01/servletTestForm/form02_Radio.jsp">Radio</a>&nbsp; 
&nbsp;<a href="/web01/servletTestForm/form03_Check.jsp">Check</a>&nbsp; 
&nbsp;<a href="/web01/servletTestForm/form04_Select.jsp">Select</a>&nbsp; <br>

&nbsp;<a href="/web01/flow01">Flow01</a>&nbsp; 
&nbsp;<a href="/web01/sessioni">SessionI</a>&nbsp; <br>

&nbsp;<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ</a>&nbsp; 
&nbsp;<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ</a>&nbsp; 
&nbsp;<a href="/web01/list2">M02ListJ</a>&nbsp; 


</body>
</html>