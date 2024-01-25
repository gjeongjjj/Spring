<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
</head>
<body>
<h2>** Web MVC2 UpdateForm **</h2>

<form action="update" method="post" >
<table>
	<caption>정보수정</caption>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="id"> I D </label> </td>
		<td> <input type="text" name="id" id="id" value="${requestScope.apple.id }" size="20" readonly> </td>
		<!-- readonly 서버로 전송( 권장)
		disabled 서버로 전송되지 않음. -->
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="password"> PASSWORD </label> </td>
		<td> <input type="password" name="password" id="password" value="${requestScope.apple.password }" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="name"> NAME </label> </td>
		<td> <input type="text" name="name" id="name" value="${requestScope.apple.name }" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="age"> AGE </label> </td>
		<td> <input type="text" name="age" id="age" value="${requestScope.apple.age }" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="jno"> JNO </label> </td>
		<td>  
			<select name="jno" id="jno">
				<option value="1" ${apple.jno == 1? "selected" : "" } >1조 : Business</option>
				<option value="2" ${apple.jno == 2? "selected" : "" } >2조 : static</option>
				<option value="3" ${apple.jno == 3? "selected" : "" } >3조 : 칭찬해조</option>
				<option value="4" ${apple.jno == 4? "selected" : "" } >4조 : 카톡으로얘기하조</option>
				<option value="7" ${apple.jno == 7? "selected" : "" } >7조 : 칠면조</option>
				<!-- requestScope.apple.jno 라고 해도됨. 원래 이게 정석.  -->
			</select>
		</td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="info"> INFO </label> </td>
		<td> <input type="text" name="info" id="info" value="${requestScope.apple.info }" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="point"> POINT </label> </td>
		<td> <input type="text" name="point" id="point" value="${requestScope.apple.point }" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="birthday"> BIRTHDAY </label> </td>
		<td> <input type="date" name="birthday" id="birthday" value="${requestScope.apple.birthday }" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="rid"> 추천인 </label> </td>
		<td> <input type="text" name="rid" id="rid" value="${requestScope.apple.rid }" size="20"> </td>
	</tr>
	<tr height="40">
		<td></td>
		<td> 
			<input type="submit" value="수정 완료" > &nbsp; &nbsp;
			<input type="reset" value="재입력" >
			
		</td>
	</tr>
	
</table>

</form>
            
<hr>
<c:if test="${!empty requestScope.message }">
	=> ${requestScope.message } <br>
</c:if>

<hr>

&nbsp; <a href="/spring02/home">Home</a>&nbsp; 
&nbsp; <a href="javascript:history.go(-1)">BACK</a>&nbsp; 


</body>
</html>