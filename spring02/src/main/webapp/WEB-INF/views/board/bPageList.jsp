<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MVC2 BoardList **</title>
	<link rel="stylesheet" type="text/css" 
		href="/spring02/resources/myLib/myStyle.css" >
 
</head>
<body>
<h2>** Spring MVC2 BoardList **</h2>
<c:if test="${!empty requestScope.message }">
	<!-- 리스트 출력하기 전에 메세지가 있다면 그거 출력하기 .  -->
	=> ${requestScope.message }<br><hr>
</c:if>

<table border="1" style="width: 100%">
	<tr>
		<th>Seq</th>
		<th>Title</th>
		<th>I D</th>
		<th>RegDate</th>
		<th>조회수</th>
	</tr>
	<c:if test="${!empty requestScope.boardAll}">
		<!-- 있으면 출력 없으면 안출력 -->
		<c:forEach var="bo" items="${requestScope.boardAll}" >
			<!-- requestScope.banana 리스트 안에 있는걸 ba에 넣어서 하나하나 읽을거임 -->
			<tr>
				<!-- jstl 표현식은 getter포함되어 있어서 굳이 getter안써도 됨.  -->
				<td>${bo.seq}</td>
				<td>
					<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가 -->
					<c:if test="${bo.indent > 0 }">
						<c:forEach begin="1" end="${bo.indent }" >
							<span>&nbsp;&nbsp;</span>
						</c:forEach>
						<span style="color: coral;">re:</span>
					</c:if>
					<c:if test="${!empty loginID }">
						<a href="detail?jCode=D&seq=${bo.seq}">${bo.title}</a>
					</c:if>
					<c:if test="${empty loginID }">
						${bo.title}
					</c:if>
				</td>
				<td>${bo.id}</td>
				<td>${bo.regdate}</td>
				<td>${bo.cnt}</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty requestScope.boardAll }">
		<tr>
			<td colspan="5"> ( ￣^￣) 출력 자료가 없습니다. ~~ </td>
		</tr>
	</c:if>
</table>
<hr>
<hr>
<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성

     1) FirstPage, Prev  -->
	<c:choose>
		<c:when test="${pageMaker.prev && pageMaker.spageNo > 1 }">
			<a href="bPageList?currPage=1 & rowsPerPage=5">FP</a>&nbsp;
		<a href="bPageList?currPage=${pageMaker.spageNo-1} & rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
    	</c:when>
		<c:otherwise>
			<font color="Gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
		</c:otherwise>
	</c:choose>

<!-- 2) Display PageNo 
	=> currPage 제외한 PageNo 에는 a태그 링크 붙어야 한다. 
	
-->
	<c:forEach var="i" begin="${pageMaker.spageNo }" end="${pageMaker.epageNo }">
		<c:if test="${i==pageMaker.cri.currPage }">
			<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage }">
			<a href="bPageList?currPage=${i} & rowsPerPage=5">${i}</a>&nbsp;
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage 
	=> ver01: makeQuery() 메서드 사용
	=> ver02: searchQuery() 메서드 사용 -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo > 1 }">
			&nbsp;<a href="bPageList?currPage=${pageMaker.epageNo+1 } & rowsPerPage=5">&GT;</a>
			&nbsp;<a href="bPageList?currPage=${pageMaker.lastPageNo } & rowsPerPage=5">LP</a>
		</c:when>
		<c:otherwise>
			<font color="Gray">&nbsp;&GT;&nbsp;LP</font>
		</c:otherwise>
	</c:choose>
      
</div>
<hr>
<c:if test="${!empty loginID }">
	&nbsp; <a href="boardInsert">글 등록하기 </a>&nbsp;
</c:if>


<hr>
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>