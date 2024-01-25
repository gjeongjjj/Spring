package com.ncs.spring02.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;


//** IOC / DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller 
// -> 사용자 요청을 제어하는 Controller 클래스
// -> DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.    
// -> interface Controller 의 구현의무 없어짐
// -> 이로인해 메서드 handleRequest()의 오버라이딩 의무 없어짐
// -> 이로인해 메서드명, 매개변수, 리턴타입(ModelAndView, String, void 중 선택)에 자유로워짐. 
// -> 그리고 클래스와 메서드 단위로 매핑이 가능한 @RequestMapping 사용가능
// -> 그러므로 하나의 컨트롤러 안에 여러개의 맵핑 메서드의 구현이 가능해짐. 
// -> 그래서 주로 테이블(엔티티) 단위로 작성함. (MemberController.java)
//
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//       DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가



@Controller
@RequestMapping(value = "/member" )
public class MemberController {
	
	@Autowired(required = false)
	MemberService service;
	
	// mlist
	@RequestMapping(value = {"/memberList"}, method = RequestMethod.GET)	
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
	}
	
	// loginForm
	
//	// => ver01. return String
//	public String loginForm(Model model) {
//		return "member/loginForm";
//	}// loginForm
	
	// => ver02. return void
	// => vireName 생략 : 
	//		- 요청명과 동일한 viewName 을 찾음.
	//		- "/WEB-INF/views/member/loginForm.jsp" 가 완성됨.
	
	@RequestMapping(value = "loginForm", method = RequestMethod.GET)
	public void loginForm() {
		
	}// loginForm
	
	//login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {
//		=> 매핑 메서드의 인지객체 와 동일한 컬럼명의 값은 자동으로 할당한다. 
//			아래 구문은 필요없음.
//		String id = request.getParameter("id");
//		dto.setId(id);
//		String password = request.getParameter("Password");
				
		
		// 1. 요청분석
		// => requst 로 전달되는 id, password 처리:
		// 매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		// ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관
		String password = dto.getPassword();
		String uri = "redirect:/"; // 성공시

		// 2. 서비스 & 결과 처리
		// => id 확인
		// => 존재하면 Password 확인
		// => 성공: id, name은 session에 보관, home 으로
		// => 실패: 재로그인 유도
		
		dto = service.selectOne(dto.getId());
		if ( dto != null && dto.getPassword().equals(password)) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			// 싫패
			uri = "member/loginForm";
			model.addAttribute("message", "로그인 실패. 다시하세요. id 또는 password 오류. ");
		}
		return uri;
	} // login
	
	//logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	} //logout
	
	// ** Member Detail
	// => 단일 Parameter 의 경우 @RequestParam("...") 활용
	//    String jCode = request.getParameter("jCode")과 동일
	//    단, 해당하는 Parameter가 없으면 400 오류 
	//    그러므로 detail 요청에도 ? jCode=D를 추가함. 
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode ) {
		// 1. 요청분석
		// => id : session에서 get
		
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail"; // detail
		
		// => update 요청 확인 후 uri 수정
		if ("U".equals(jCode)) {
			uri = "member/updateForm";
		}
		
		// 2. Service & 결과 처리
		model.addAttribute("apple", service.selectOne(id));
		return uri;
		
	}// detail

	
	
	// joinForm
	@RequestMapping(value = "joinForm", method = RequestMethod.GET)
	public void joinForm() {
	}// joinForm
	
	// join
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, MemberDTO dto) {
		// 1. 요청 분석
		// => 이전 : 한글처리 , request 값-> dto에 set
		// => 스프링 : 한글은 filter, request 처리는 매개변수로 자동화. 
		String uri = "member/loginForm"; // 성공시
		
		// 2. Service & 결과
		if (service.insert(dto) > 0) {
			// 성공
			model.addAttribute("message", "회원가입 성공(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ 로그인 후 이용하세요 !!");
		} else {
			// 실패 : 재가입 유도 
			uri = "member/loginForm";
			model.addAttribute("message", "회원가잆 실패. 다시 시도해주세요. ");
			
		}
		return uri;
	}// join
	
	
	// update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, Model model, MemberDTO dto) {
		// 1. 요청 분석
		// => 성공 : MemberDetail
		// => 실패 : 다시 updateForm으로.
		// => 두 경우 모두 출력하려면 dto 객체의 값("apple")이 필요하므로 보관. 
		
		String uri = "member/memberDetail"; // 성공시
		model.addAttribute("apple", dto);
		
		// 2. Service & 결과
		if (service.update(dto) > 0) {
			// 성공
			model.addAttribute("message", "회원 정보 수정 성공(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ 로그인하세요 ");
			// => name을 수정할수도 있으므로 loginName을 수정해준다. 
			session.setAttribute("loginName", dto.getName());
			
		} else {
			// 실패 : 재가입 유도 
			uri = "member/updateForm";
			model.addAttribute("message", "회원 정보 수정 실패 !! 다시 하세요!!");
			
		}
		return uri;
	}// update

	
	// delete
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model) {
		// 1. 요청분석
		// => id : session에서 get
		// => delete & session 처리
		
		String id = (String) session.getAttribute("loginID");
		String uri = "redirect:/home"; 
		
		// 2. Service & 결과 처리
		if (service.delete(id) > 0) {
			// 성공
			model.addAttribute("message", "탈퇴 성공 ~! ( ﾟдﾟ)つ Bye");
			session.invalidate();
		} else {
			model.addAttribute("message", "탈퇴 실패 !! 관리자에게 연락하세요.");
			
		}
		return uri;
		
	}// delete

	
	
}// class
