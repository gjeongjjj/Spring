<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** forTokens, PageFlow (import, redirect) **</title>
</head>
<body>
<h2>1. forTokens</h2>
<pre><b>
=> 구분자로 분리된 각각의 토큰을 처리할때 사용됨.
=> test 1.1) 단일 구분자
<c:forTokens var="city" items="성남,용인, 서울# 부산, Paris, NewYork" delims="," >
	${city}
</c:forTokens>

=> test 1.2) 복수 구분자
<c:forTokens var="city" items="성남,용인, 서울#부산, Paris!NewYork" delims=" ,#!">
	${city }
</c:forTokens>


<h2>2. import</h2>
=> directive : include -> 소스코드 포함, 변수공유 가능
=> jsp : include -> 웹Page 포함, 변수공유 불가능
=> jstl : import -> 웹Page 포함, 변수공유 불가능

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
<c:import url="../jsp01/ex01_HelloJsp.jsp">
</c:import>

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

<h3>3. redirect</h3>
=> response.sendRedirect() 와 동일
=> 웹 브라우저의 주소창의 url이 변경됨
=> c:redirect url="/jsp01/ex01_HelloJsp.jsp" // web01부터 시작하면 오류남 -->
<%-- <c:redirect url="/jsp01/ex01_HelloJsp.jsp"/> --%>
<h3>4. url</h3>
=> Value 를 url로 인식 시켜줌_set 으로 정의해도 결과는 동일
=> test 4.1) a_Tag Link
	-> c:url 과 c:set은 경로가 다르다. 
	   c:url은 프로젝트 내부에서 찾아주고, c:set은 절대 경로 값(프로젝트 외부)
<c:url value="/jsp01/ex01_HelloJsp.jsp" var="urlTest" />
<a href="${urlTest}">urlTest</a>
<c:set value="/web01/jsp01/ex01_HelloJsp.jsp" var="setUrlTest"/>
<a href="${setUrlTest}">setUrlTest</a>


=> test 4.2) image
<c:url value="../images/aaa.gif" var="aaa" />
<img alt="urlTest" src="${aaa}">

</b></pre>

</body>
</html>