package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.JoDTO;
import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/jo")
@AllArgsConstructor
public class JoController {
	
	JoService service;
	MemberService mService;
	
	// joCaptain
	@GetMapping("/joCaptain")
	public void joCaptain(Model model) {
		model.addAttribute("joCaptain", service.joCaptain());
	}
	
	//joDetail
	@GetMapping("/jodetail") 
	public String detail(Model model, Jo entity, 
									@RequestParam("jCode") String jCode) {
		String uri = "jo/joDetail";
		model.addAttribute("joOne", service.joDetail(entity.getJno()));
		//업데이트
		if ("A".equals(jCode)) {
			uri = "jo/joUpdate";
		}
		// ** 조원목록 출력하기 추가 (detail 출력시에만 )
		// => MemberService 실행 
		// 	  -> findByJno(int jno) 메서드 추가
		//    -> 실행결과는 banana로 
//		if("B".equals(jCode)) {
//		}
		model.addAttribute("joOneList", mService.findByJno(entity.getJno()));
		
		return uri;
	}
	
	// insert Form
	@GetMapping("joInsert")
	public void joInsert() {	
	} //insert Form
	
	// insert
	@PostMapping("/insert")
	public String insert(Model model, JoDTO dto, Jo entity, RedirectAttributes rttr) {
		String uri = "redirect:joCaptain";
		
		try {
			log.info("** jo insert 성공 \n=> "+ service.save(entity));
			rttr.addFlashAttribute("message", "조 등록 완료");
		} catch (Exception e) {
			log.info("** jo insert 실패 \n=> "+ e.toString());
			rttr.addFlashAttribute("message", "등록 실패. 다시 시도해주세요. ");
		}
		return uri;
		
	}
	
	//update
	@PostMapping("/update") //a태그는 GET
	public String update(Model model, JoDTO dto, Jo entity, RedirectAttributes rttr) {
		
		String uri = "redirect:joCaptain"; 
		model.addAttribute("newJo", entity);
		
		try {
			log.info("** 조 업데이트 완료 \n=> " + service.save(entity));
			rttr.addFlashAttribute("message", "조 업데이트 완료");
		} catch (Exception e) {
			log.info("** 조 업데이트 실패 \n=> " + e.toString());
			rttr.addFlashAttribute("message", "조 업데이트 실패. 다시 하세요.");
		}

		return uri;
	}
	
	// delete
	@GetMapping("/delete")
	public String delete(int jno, RedirectAttributes rttr) {
		String uri = "redirect:joCaptain";
		
		try {
			service.joDelete(jno);
			log.info("** member Delete 성공 => "+jno);
			rttr.addFlashAttribute("message", "조 삭제 완료");
		} catch (Exception e) {
			rttr.addFlashAttribute("message", "조 삭제 안됨. 다시 시도하세요.");
		}
		
		
		
		
		
		return uri;
	}
	
	
	
	
	
}
