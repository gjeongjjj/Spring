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
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<table border="1" style="width: 100%" id="myNewList">
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
		<!-- DELETE 기능 추가 -->
		<th>Delete</th>
	</tr>
	<c:if test="${!empty requestScope.banana }">
		<!-- 있으면 출력 없으면 안출력 -->
		<c:forEach var="ba" items="${requestScope.banana}" >
			<!-- 
				** idbList : id별 boardList
				=> 선택된 id를 function에 전달. (매개변수를 활용함. )
				   idbList('banana')
			 -->
			<tr>
				<%-- <td>${ba.id}</td> --%>
				<td><span class="textlink" onclick="idbList('${ba.id}')">${ba.id}</span></td>
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
				<!-- DELETE 기능 추가 
					=> 선택된 id를 function에 전달. (매개변수를 활용함. )
					=> 결과는 성공/실패 여부만 전달: RESTController
					=> 성공 : Deleted 로 변경해주고, onclick 이벤트 해제. 
				-->
				<td> <span class="textlink" id="${ba.id}" onclick="axiDelete('${ba.id}')" >DELETE</span> </td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.banana }">
		<tr>
			<td colspan="10"> ( ￣^￣) 출력 자료가 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<hr>

&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>