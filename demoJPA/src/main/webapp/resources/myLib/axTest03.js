
// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response (axmemberList.jsp)
//   - idbList(id별 boardList) : RTestController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// 1. List 출력 
// 1.1) Page response
// 		=> response 를 resultArea1 에 출력하기. 
// 		=> 요청명 : /member/aximlist
// 		=> response: axmemberList.jsp

"use strict"

function axiMList() {
	
	let url = "/member/aximlist";
	
	axios.get(url
	).then(response=> {
		console.log("response 성공");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		alert("response 실패 ->" + err.message);
	})
	document.getElementById('resultArea2').innerHTML = '';
	
} //axiMList

// 1.2) idbList(id별 boardList)
// => RESTController, Pathvariable 처리, List_Data response
// => Server : service, Sql 구문 작성
// => request : id를 path로 전송 /rest/idblist/banana

// => response 
//	-> 성공 : 반복문, Table로 List 출력문 완성, resultArea2에 출력
//  -> 출력자료의 유/무 : server에서 status로 502로 처리
//  -> 실패 : resultArea2 clear, alert 으로 에러메세지 출력
function idbList(id) {
	let url = "/rest/idblist/"+id;
	axios.get(url
	).then(response => {
		alert("** 성공 => resultArea2에 List 작성 **");
		console.log("** result List_Data => "+response.data);
		
		let listData = response.data;
		let resultHtml = 
			`
			<table border="1" style="width: 100%">
				<tr>
					<th>Seq</th>
					<th>Title</th>
					<th>I D</th>
					<th>RegDate</th>
					<th>조회수</th>
				</tr>
			`;
			
		// => 반복문 적용 ((게시물 여러개면 반복문으로 다 그려줘야 함. ))
		for( let bo of listData){
			resultHtml += 
			`
				<tr>
					<td>${bo.seq}</td>
					<td>${bo.title}</td>
					<td>${bo.id}</td>
					<td>${bo.regdate}</td>
					<td>${bo.cnt}</td>
				</tr>
  // ** for 간편출력 : of, in
  // => in: undifined 는 통과하고, 배열(index Return), 객체(속성명 Return)
  // => of: undifined 까지 모두출력 (순차출력과 동일), value 를 return, 
  //        ES6 에 for ~ in 의 단점을 보완 개선하여 추가됨.
  //        일반 객체에는 적용안되지만, (오류발생, 개발자모드로 확인가능)
  //         Array, String, Map, Set, function의 매개변수 객체 와
  //        이터러블 규약을 따르는 이터러블 객체 (Iterable Object) 는 적용됨
  // => 이터러블 규약
  //      내부에 Symbol.iterator (줄여서 @@iterator로 표현하기도함) 메서드가 구현되어 있어야 한다는 규약 

			`;
		}
		resultHtml +=`</Table>`;
		
		document.getElementById('resultArea2').innerHTML = resultHtml;
		
	}).catch(err => {
		// => response 의 status 값이 502면 출력자료 없음. 
		if(err.response.status=='502'){
			document.getElementById('resultArea2').innerHTML
					= err.response.data;
			alert("** 작성된 게시물이 없습니다.  **");
		} else {
			document.getElementById('resultArea2').innerHTML="";
			alert("** 시스템 오류. 잠시 후 다시 하세요 => **" + err.message);
		} 
		
	});
	
} //idbList

// 2.2 ) axiDelete
// => 요청 : "/rest/axidelete"  PathVariable 적용
// => Response : 결과는 성공/실패 여부만 전달받음, 그러므로 RESTController 로.
// => 성공 : Deleted 로 변경해주고, onclick 이벤트 해제. 

// => event 객체 적용하기
// => document.getElementById(id) 대신 
//	  e.target 으로 이벤트 발생 대상 객체 인식 가능

function axiDelete(e, id) {
	let url = "/rest/axidelete/"+id;
	axios.delete(url
	).then(response => {
		alert("** 삭제 성공 **" + response.data);
		// => 삭제 성공
		//    * Delete -> Deleted, Gray_color, Bold 로
		//    * onclick 이벤트 제거. 
		// 	  * Style 제거
		/*document.getElementById(id).innerHTML="";*/
		/*document.getElementById(id).innerHTML = "Deleted";*/
		
		// => e.target 적용
		e.target.innerHTML = "Deleted";
		document.getElementById(id).style.color = "gray";
		document.getElementById(id).style.fontWeight = "bold";
		
		document.getElementById(id).classList.remove('textlink');
		document.getElementById(id).removeAttribute('onclick');
		
	}).catch(err => {
		if (err.response.status == '502'){
			alert("err.response.data => "+ err.response.data)
		} else {
			alert("시스템 오류, 잠시후 다시 하세요. => "+ err.message)
		}
	})
	
	
	
} //axiDelete



// 2.3) JoDetail
// 2.3.1) MouseOver : showJoDetail
// 	=> jno mouseover : JoDetail content Div에 출력 (마우스포인터 위치에)
//  => request : axios, get, RESTController 에 "/jodetail" 요청
//  => response : 성공시 JoDTO 객체 보내기. 
function showJoDetail(e, jno) {
	// ** 마우스포인터 위치 확인
	// => 이벤트객체 활용
	// 	  - event 객체 (이벤트핸들러 첫번째 매개변수) 가 제공
	//    - event 객체 프로퍼티 : type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)
	//    - e.pageX, e.pageY : 전체 Page 기준
	//    - e.clientX, e.clientY : 보여지는 화면 기준 -> page Scroll 시에 불편함. 
	console.log(`** e.pageX = ${e.pageX}, e.pageY = ${e.pageY} **`);
	console.log(`** e.clientX = ${e.clientX}, e.clientY = ${e.clientY} **`);
	
	let url = "/rest/jodetail/"+jno;
	let mleft = e.pageX +20;
	let mtop = e.pageY;
	
	axios.get(url
	).then(response => {
		console.log(`** response 성공 => ${response.data}`);
		
		let listData =response.data;
		console.log(listData);
		let resultHtml = 
			`
				<table>
					<tr height="50">
						<th>Jno</th>
						<td>${listData.jno}</td>
					</tr>
					<tr height="50">
						<th>Jname</th>
						<td>${listData.jname}</td>
					</tr>
					<tr height="50">
						<th>Captain</th>
						<td>${listData.captain}</td>
					</tr>
					<tr height="50">
						<th>Project</th>
						<td>${listData.project}</td>
					</tr>
					<tr height="50">
						<th>Slogan</th>
						<td>${listData.slogan}</td>
					</tr>
				</table>
			`
		document.getElementById('content').style.visibility = 'visible';
		document.getElementById('content').innerHTML = resultHtml;
		document.getElementById('content').style.left = mleft+"px";
		document.getElementById('content').style.top = mtop+"px";
		
		
	}).catch(err => {
		if ( err.response.status == '502') alert(err.response.status);
		else alert("** 시스템 오류, 잠시후 다시 하세요 => " + err.message);
	});
	
	
} //showJoDetail








// 2.3.2) MouseOut : hideJoDetail
// => 화면에 표시되어 있던 content Div 가 사라짐. 
function hideJoDetail() {
	
	document.getElementById('content').style.visibility = 'hidden';
	
		
} //showJoDetail









// ** Ajax Member_PageList *********************
// => axiMList 에 Paging + 검색기능 추가
// => 검색조건 & Paging , Ajax 구현
//    -> 입력된 값들을 서버로 전송요청: axios
//   -> url 완성후 axios 호출

// => 1) 검색조건 입력 후 버튼클릭
//   -> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨
//    ${pageMaker.makeQuery(1)} -> ?currPage=1&rowsPerPage=5 

// 1. 검색조건 입력 후 버튼 클릭
function searchDB() {
	let url = 'axmcri'
		+ '?currPage=1&rowsPerPage=5'
		+ '&searchType=' + document.getElementById('searchType').value
		+ '&keyword=' + document.getElementById('keyword').value;
	axiMListCri(url); // axios 호출
}

// 2. searchType 을 '전체' 로 변경하면 keyword는 clear
function keywordClear() {
	if (document.getElementById('searchType').value == 'all') {
		document.getElementById('keyword').value = '';
	}
} 

// 3. axios Code
function axiMListCri(url){
	url = "/member/"+url;
	alert(`axiMListCri url = ${url}`);
	
	axios.get(url
	).then(response => {
		console.log("** response 성공 ** ");
		document.getElementById('resultArea1').innerHTML = response.data;
	}).catch(err => {
		document.getElementById('resultArea1').innerHTML = "** axiMListCri 실패 => " + err.message;
	});
	
	document.getElementById('resultArea2').innerHTML = "";
}

// 4. Check 검색기능 추가
// => Check  검색 submit을 Button(type 속성주의)으로 변경
// => MemberController : axmcri 메서드 공유
// => 단, 조건 구분을 위해 요청명은 "/axmcheck"
function axiMListCheck() {
	// => 첫 요청 url 생성
	// url=/axmcheck?currPage=1&rowsPerPage=5&check=1&check=2&check=3
	
	let checkAll = document.querySelectorAll(".check");
	let checkData = "";
	
	/*for(let i = 0; i < checkAll.length; i++ ){
		if (checkAll[i].checked){
			checkData += "&check="+checkAll[i].value;
		}
	}*/
	// forEach 써서. 
	checkAll.forEach( check => {
		if (check.checked){
			checkData += "$check"+check.value;
		}
	});
	
	let url = 'axmcheck'
				+'?currPage=1&rowsPerPage=5'
				+checkData;
	axiMListCri(url); // axios 호출

} //axiMListCheck

// 5. Board Check_List
function checkClear() {
	// document.querySelectorAll('.clear') = false;
	// => nodeList 를 반환하기 때문에 적용안됨
	let ck = document.querySelectorAll('.check');
	for (let i = 0; i < ck.length; i++) {
		ck[i].checked = false;
	}
	return false; // reset 의 기본이벤트 제거 

} //checkClear









