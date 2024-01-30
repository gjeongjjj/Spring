package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	BoardService service;
	
	// ** Reply Insert 
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) { 
		// => 답글 처리를 위해 부모글의 root, step, indent 를 인자로 전달받으면, 
		//    이 인자에 담겨진 값은 requestScope 과 동일. 
		// => 그러므로 response 전송 전까지는 서버(Jsp) 에서 사용 가능
		//    단, 객체명의 첫 문자를 소문자로 해서 접근 가능. ( ${boardDTO.~~} )
	}
	
	// => 메서드명과 요청명이 위의 메서드와 동일하지만, 
	//    Post 요청이고 인자가 다르기 때문에 허용됨. 무관함. 
	@PostMapping("/replyInsert")
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		// => 성공시 : boardList 입력완료 확인 
		// => replyInsert 재입력 유도
		String uri = "redirect:boardList";
		
		// => dto 값 확인
		//    -> id, title, content : 사용가능
		//    -> 부모글의 root : 사용가능
		//    -> 부모글의 step, indent : 1씩 증가 
		// => Sql 처리
		//    -> replyInsert, step 의 Update
		dto.setStep(dto.getStep() + 1);
		dto.setIndent(dto.getIndent() + 1);
		System.out.println(dto);
		if (service.rinsert(dto) > 0) {
			rttr.addFlashAttribute("message", "~~ 답글 등록 성공 ~~");
		} else {
			uri = "board/replyInsert";
			model.addAttribute("message", "답글 등록 실패...다시 써주세요 -3- ");
		}
		return uri;
	} //replyInsert
	
	// ** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("boardAll", service.selectList());
	
	} //boardList
	
	// ** Board Detail
	// => 글 요청 처리중, 글 읽기 전. 
	// => 조회수 증가
	//    -> loginID 와 board에 있는 id 가 다른 경우.
	@GetMapping("/detail")
	public String boardDetail(HttpSession session, Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		
		String uri = "board/boardDetail";
		
		if ("U".equals(jCode)) {
			uri = "board/boardUpdate";	
		}
		// => 조회수 증가 : SelectOne 의 결과를 보관
		BoardDTO dto = service.selectOne(seq);
		System.out.println(dto);
		if (!dto.getId().equals((String)session.getAttribute("loginID"))) {
			// 조회수 증가 조건 만족
			dto.setCnt(dto.getCnt() + 1);
			service.update(dto);
		}
		
		model.addAttribute("boardOne", dto);
		
		return uri;
	}
	
	// insertForm
	@RequestMapping(value = "boardInsert", method = RequestMethod.GET)
	public void boardInsert() {
	} // insertForm
	
	// ** Insert 
	@RequestMapping(value = "/insertb", method = RequestMethod.POST)
	public String insert (Model model, BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		if (service.insert(dto) > 0) {
			rttr.addFlashAttribute("message", "게시글 등록 완료");
		} else {
			uri = "board/boardInsert";
			rttr.addFlashAttribute("message", "게시글 등록 실패");
			// 여기 rttr이 아니라 addAttribute로 해야됨. 리다이렉트가 아니기 때무에. 
		}			
		return uri;
	} //insert
	
	//update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String boardUpdate(Model model, BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		System.out.println("왜 안들어갈까요" + dto);
		model.addAttribute("boardOne", dto); // 실패했을 때 원래의 값을 다시 저장하는 용도. 
		
		if (service.update(dto) > 0) {
			rttr.addFlashAttribute("message", "업데이트 완료!");
			
		} else {
			uri = "board/boardUpdate";
			rttr.addFlashAttribute("message", "업데이트 실패...");
		}
		return uri;
	} //update
	
	//delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(BoardDTO dto, @RequestParam("seq") Integer seq, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		
		if (service.delete(seq) > 0) {
			rttr.addFlashAttribute("message", "게시글 삭제 완료");
		} else {
			uri = "board/boardList";
			rttr.addFlashAttribute("message", "게시글 삭제 완료");
		}
		return uri;
	}
	
	
	
} // class
