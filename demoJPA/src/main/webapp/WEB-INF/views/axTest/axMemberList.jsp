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
				
				=> a Tag에 이벤트 적용시 책갈피 기능 활용 가능
					-> href : 적용하지 않음 (이동하지 않음)
					-> href = "#id" : id 위치로 이동, "#" : 최상단으로 이동. 
					-> href = "javascript:;" : 이동하지 않음. 
				
			 -->
			<tr>
				<%-- <td>${ba.id}</td> --%>
				<%-- <td><span class="textlink" onclick="idbList('${ba.id}')">${ba.id}</span></td> --%>
				<td> <a href="#resultArea2" class="textlink" onclick="idbList('${ba.id}')">${ba.id}</a></td>
				<%-- <td>${ba.password}</td> --%>
				<td>${ba.name}</td>
				<td align="center">${ba.age}</td>
				
				<!-- 여기에 Jo 정보 Div에 출력 -->
				<td align="center">
					<span class="textlink" onmouseover="showJoDetail(event, ${ba.jno})" 
										   onmouseout="hideJoDetail()">${ba.jno}</span>
				</td>
				
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
							 이를 위해 Delete Tag 를 function에서 인식할 수 있어야 함. 
							 
					** function 에 이벤트 객체 전달
					=> 이벤트 핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함. 
					=> a Tag 와 span 사용시 e.target 값 비교
						-> a Tag : "javascript:;"
						-> span : [object HTMLSpanElement]
				-->
				<td> <span class="textlink" id="${ba.id}" onclick="axiDelete(event, '${ba.id}')" >DELETE</span> </td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.banana }">
		<tr>
			<td colspan="10"> ( ￣^￣) 출력 자료가 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<div id="content"></div>
<hr>

&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>