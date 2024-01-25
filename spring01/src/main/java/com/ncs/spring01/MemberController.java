package com.ncs.spring01;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.MemberService;

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
public class MemberController {
	@Autowired(required = false)
	MemberService service;
	
	// mlist
	@RequestMapping(value = {"/mlistsp", "mlist"}, method = RequestMethod.GET)	
	public String mList(Model model) {
		
		model.addAttribute("banana", service.selectList());
		
		return "member/memberList";
	}
	
	// Member Detail
	@RequestMapping(value = {"/mdetailsp", "/mdetail"}, method = RequestMethod.GET)
	public String mDetail(Model model) {
	
		model.addAttribute("apple", service.selectOne("jjang9"));
		return "member/memberDetail";
		
		
	}


	
	
}// class
