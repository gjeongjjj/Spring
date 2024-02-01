<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** LoginForm **</title>
<link rel="stylesheet" type="text/css" 
		href="/spring02/resources/myLib/myStyle.css" >
<script src="/spring02/resources/myLib/inCheck.js"></script>
<script>
	let iCheck=false;
	let pCheck=false;
	
	onload=function() {
		   document.getElementById('id').focus();
		   document.getElementById('id').addEventListener('keydown',
			(e)=>{
			   if (e.which==13) {
			      e.preventDefault();
			      // => form 에서는
			      // => enter 누르면 자동 submit 발생되므로 이를 제거함
			      document.getElementById('password').focus();
			   }//if
			});
		   // -> 무결성 점검 
		    document.getElementById('id').addEventListener('focusout', ()=>{ iCheck=idCheck(); });
		   
		   // => Password
		    document.getElementById('password').addEventListener('keydown',
		         (e)=>{
		            if (e.which==13) {
		               e.preventDefault();
		               document.getElementById('submitTag').focus();
		            }//if
		         });
		   // -> 무결성 점검
		   document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
		   
	}
	function inCheck (){
		if (!iCheck) { document.getElementById('iMessage').innerHTML=' 당신의 id 를 입력하세요'; }
		if (!pCheck) { document.getElementById('pMessage').innerHTML=' 필수입력, password 를 입력하세요'; }
		
		if (iCheck && pCheck){
			return true;
		} else {
			return false;
		}
		
	}
	
	
</script>


</head>
<body>
<h2>** Web MVC2 LoginForm **</h2>
<form action="login" method="post" >
<table>
	<tr height="40">
		<td bgcolor="pink"> <label for="id"> I D </label> </td>
		<td> 
			<input type="text" name="id" id="id" size="10"> 
			<br> <span id="iMessage" class="eMessage"> </span>
		</td>
		
	</tr>
	<tr height="40">
		<td bgcolor="lightblue"> <label for="password"> Password </label> </td>
		<td> 
			<input type="password" name="password" id="password" size="10">
			<br> <span id="pMessage" class="eMessage"> </span> 
		</td>
	</tr>
	<tr>
		<td></td>
		<td> 
			<input type="submit" id="submitTag" value="로그인" > &nbsp;&nbsp;
			<input type="reset" value="취소">
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