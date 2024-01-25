<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoinForm **</title>
</head>
<body>
<h2>** Web MVC2 JoinForm **</h2>

<form action="/web02/mjoin" method="post" >
<table>
	<caption>회원가입</caption>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="id"> I D </label> </td>
		<td> <input type="text" name="id" id="id" placeholder="영문과 숫자 4~10글자" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="password"> PASSWORD </label> </td>
		<td> <input type="password" name="password" id="password" placeholder="특수문자를 반드시 사용하세요." size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="name"> NAME </label> </td>
		<td> <input type="text" name="name" id="name" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="age"> AGE </label> </td>
		<td> <input type="text" name="age" id="age" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="jno"> JNO </label> </td>
		<td>  
			<select name="jno" id="jno">
				<option value="1">1조 : Business</option>
				<option value="2">2조 : static</option>
				<option value="3">3조 : 칭찬해조</option>
				<option value="4">4조 : 카톡으로얘기하조</option>
				<option value="7">7조 : 칠면조</option>
			</select>
		</td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="info"> INFO </label> </td>
		<td> <input type="text" name="info" id="info" placeholder="자기소래 & 희망사항" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="point"> POINT </label> </td>
		<td> <input type="text" name="point" id="point" placeholder="실수 입력" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="birthday"> BIRTHDAY </label> </td>
		<td> <input type="date" name="birthday" id="birthday" size="20"> </td>
	</tr>
	<tr height="40">
		<td bgcolor="RosyBrown"> <label for="rid"> 추천인 </label> </td>
		<td> <input type="text" name="rid" id="rid" size="20"> </td>
	</tr>
	<tr height="40">
		<td></td>
		<td> 
			<input type="submit" value="회원가입" > &nbsp; &nbsp;
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

&nbsp; <a href="/web02/home.jsp">Home</a>&nbsp; 
&nbsp; <a href="javascript:history.go(-1)">BACK</a>&nbsp; 




</body>
</html>