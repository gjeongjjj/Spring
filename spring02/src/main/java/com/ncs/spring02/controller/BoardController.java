package com.ncs.spring02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	// ** Board List
	@GetMapping("/boardList")
	public void boardList(Model model) {
		model.addAttribute("boardAll", service.selectList());
	
	} //boardList
	
	// ** Board Detail
	@GetMapping("/detail")
	public String boardDetail(Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		
		String uri = "board/boardDetail";
		
		if ("U".equals(jCode)) {
			uri = "board/boardUpdate";	
		}
		model.addAttribute("boardOne", service.selectOne(seq));
		
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
			model.addAttribute("message", "게시글 등록 완료");
		} else {
			uri = "board/boardInsert";
			rttr.addFlashAttribute("message", "게시글 등록 실패");			
		}			
		return uri;
	} //insert
	
	//update
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String boardUpdate(BoardDTO dto, RedirectAttributes rttr) {
		String uri = "redirect:boardList";
		
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
		return null;
	}
	
	
	
} // class
