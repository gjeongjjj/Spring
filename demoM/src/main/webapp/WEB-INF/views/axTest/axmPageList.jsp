<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 axmPageList **</title>
	<link rel="stylesheet" type="text/css" 
		href="/resources/myLib/myStyle.css" >

 	
</head>
<body>
<h2>** Spring MVC2 axmPageList **</h2>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType=='all' ? 'selected' : '' }>전체</option>
		<option value="id" ${pageMaker.cri.searchType=='id' ? 'selected' : '' }>ID</option>
		<option value="name" ${pageMaker.cri.searchType=='name' ? 'selected' : '' }>Name</option>
		<option value="age" ${pageMaker.cri.searchType=='age' ? 'selected' : '' }>Age</option>
		<option value="birthday" ${pageMaker.cri.searchType=='birthday' ? 'selected' : '' }>Birthday</option>
		<option value="info" ${pageMaker.cri.searchType=='info' ? 'info' : '' }>Info</option>
		<option value="rid" ${pageMaker.cri.searchType=='rid' ? 'selected' : '' }>추천인</option>
	</select>
	
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	
	<button id="searchBtn" onclick="searchDB()">Search</button>
	<hr>
	<!-- 여기 체크리스트 만들거임 
	CheckBox Test-->
 	<form action="axmcheck" method="get">
		<b>JNO : </b>
		
		<!-- check의 선택한 값 유지를 위한 코드 -->
		<c:set var="ck1" value="false" />
		<c:set var="ck2" value="false" />
		<c:set var="ck3" value="false" />
		<c:set var="ck4" value="false" />
		<c:set var="ck5" value="false" />
		<c:forEach var="jno" items="${pageMaker.cri.check}">
			<c:if test="${jno=='1'}"> <c:set var="ck1" value="true" /> </c:if>
			<c:if test="${jno=='2'}"> <c:set var="ck2" value="true" /> </c:if>
			<c:if test="${jno=='3'}"> <c:set var="ck3" value="true" /> </c:if>
			<c:if test="${jno=='4'}"> <c:set var="ck4" value="true" /> </c:if>
			<c:if test="${jno=='7'}"> <c:set var="ck5" value="true" /> </c:if>
		</c:forEach>
		
		<!-- --------------------------------- -->

		<input type="checkbox" name="check" class="check" value="1" ${ck1 ? 'checked' : '' }>Business&nbsp; 
		<input type="checkbox" name="check" class="check" value="2" ${ck2 ? 'checked' : '' }>static&nbsp; 
		<input type="checkbox" name="check" class="check" value="3" ${ck3 ? 'checked' : '' }>칭찬해조&nbsp; 
		<input type="checkbox" name="check" class="check" value="4" ${ck4 ? 'checked' : '' }>카톡얘기하조&nbsp; 
		<input type="checkbox" name="check" class="check" value="7" ${ck5 ? 'checked' : '' }>칠면조&nbsp; 
		
		<!-- <input type="submit" value="Search" >&nbsp; -->
		<button type="button" onclick="axiMListCheck()">check 검색</button>
		<input type="reset" value="Clear" onclick="return checkClear()">
	</form>

</div>
<hr>
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

<hr>
<div align="center">
<!-- 1) FirstPage, Prev   -->
	<c:choose>
		<c:when test="${pageMaker.prev && pageMaker.spageNo > 1 }">
<%-- 		
			<a href="${pageMaker.searchQuery(1) }">FP</a>&nbsp;
			<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;
 --%>    	
 			<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(1)}')">FP</span>&nbsp;
 			<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.spageNo-1)}')">&LT;</span>&nbsp;
 		</c:when>
		<c:otherwise>
			<font color="Gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
		</c:otherwise>
	</c:choose>

<!-- 2) Display PageNo -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<%-- <a href="${pageMaker.searchQuery(i) }">${i}</a>&nbsp; --%>
			<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(i)}')">${i}</span>&nbsp;
			
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage  -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo > 0 }">
			<%-- 
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1) }">&GT;</a>
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo) }">LP</a>
			--%>
			<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.epageNo+1)}')">&GT;</span>&nbsp;
			<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.lastPageNo)}')">LP</span>&nbsp;

		</c:when>
		<c:otherwise>
			&nbsp;<font color="Gray">&GT;</font>
			&nbsp;<font color="Gray">LP</font>
		</c:otherwise>
	</c:choose>
</div>
<hr>

<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>