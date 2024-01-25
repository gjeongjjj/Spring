<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MVC02 Detail JSTL **</title>
</head>
<body>
<h2>** MVC02 Detail JSTL **</h2>

<table border="1" style="width: 100%">
	<c:if test="${! empty requestScope.myInfo }">
		<tr>
			<th bgcolor="Pink">Sno</th>
			<td>${requestScope.myInfo.sno }</td>
		</tr>
		<tr>
			<th bgcolor="lightblue">Name</th>
			<td>${requestScope.myInfo.name }</td>
		</tr>
		<tr>
			<th bgcolor="Pink">Age</th>
			<td>${requestScope.myInfo.age }</td>
		</tr>
		<tr>
			<th bgcolor="lightblue">Jno</th>
			<td>${requestScope.myInfo.jno }</td>
		</tr>
		<tr>
			<th bgcolor="Pink">Info</th>
			<td>${requestScope.myInfo.info }</td>
		</tr>
		<tr>
			<th bgcolor="lightblue">Point</th>
			<td>${requestScope.myInfo.point }</td>
		</tr>
	</c:if>
	
	<c:if test="${empty requestScope.myInfo }">
		<tr>
			<td colspan="6">
				<h3> 로그인이 제대로 안된거 같습니다용 </h3>
			</td>	
		</tr>
	</c:if>
	
</table>
<hr>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>

</body>
</html>