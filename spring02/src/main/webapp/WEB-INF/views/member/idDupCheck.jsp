<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** ID 중복확인 **</title>
<link rel="stylesheet" type="text/css"
	href="/Spring02/resources/myLib/myStyle.css">
	
<script src="/spring02/resources/myLib/inCheck.js"></script>

<script>

//** idOK : 사용자가 입력한 id를 사용가능하도록 해주고, 현재(this)창은 close
//1) this 창의 id 를 부모창의 id 로
//2) 부모창의 ID중복확인 버튼은 disable & submit 은 enable
//3) 부모창의 id 는 수정불가 (readonly) , password 에 focus
//4) 현재(this)창은 close

	function idOK() {
		// 1) 
		// pener.odcument.getElementById('id').value=document.getElementById('id').value;
		opener.document.getElementById('id').value = "${param.id}";
		// => EL 활용: jsp 문서에서는 script 구문의 문자열 내부에 있는 EL은 처리해줌
		
		// 2) disabled 확성화 시키기. 
		opener.document.getElementById('idDup').disabled = 'disabled';
		opener.document.getElementById('submitTag').disabled = '';
		
		// 3) 부모창 수정불가 
		opener.document.getElementById('id').readOnly = 'readOnly';
		opener.document.getElementById('password').focus();
		
		// 4) window창 닫기
		close(); // window 생략 가능, self.close(); 모두 가능.
		
	} // idOK

</script>



<style>
body {
	background-color: LightYellow;
	font-family: 맑은고딕;
}
#wrap {
	margin-left: 0;
	text-align: center;
}
h3 {
	color: navy;
}
</style>

</head>
<body>
	<div id="wrap">
		<h3>** ID 중복확인 *</h3>
		<h4>Parameter id값 확인</h4>
		=> Parameter_ID : ${param.id}<br> 
		<hr>
		<form action="idDupCheck" method="get">
			User_ID : 
			<input type="text" name="id" id="id" value="${param.id}">
			<input type="submit" value="ID중복확인" onclick="return idCheck()"><br>
			<!-- inCheck.js 의  idCheck() 의 결과에 따라 submit 결정 -->
			<span id="iMessage" class="eMessage"></span>			
		</form>
		<br>
		<br>
		<!-- 서버의 처리결과 : idUser의 값 'T' / 'F' 에 따른 화면 -->
		<div id="msgBlock">
			<c:if test="${idUse=='T'}">
				${param.id} 는(은) 사용 가능합니다. &nbsp;&nbsp;
				<button onclick="idOK()">ID_선택</button>
			</c:if>

			<c:if test="${idUse=='F'}">
				${param.id} 는(은) 사용 불가능합니다. (사용중) <br>
				다시 입력해 주세요. ^*^ <br>
				
			<!-- 부모창(joinForm, opener)에 남아있는 사용자가 입력한 id는 지워주고, 
				현재(this)창 에서는 id 에 focus 를 주고 재입력 유도 -> script 필요 
				-->
				<script>
					window.document.getElementById('id').focus();
					opener.document.getElementById('id').value='';
				</script>
				
			</c:if>

		</div>
		
	</div>

</body>
</html>