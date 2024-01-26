package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.JoService;

@Controller
@RequestMapping(value = "/jo")
public class JoController {
	
	@Autowired(required = false)
	JoService service;
	
	// joCaptain
	@RequestMapping(value = "/joCaptain", method = RequestMethod.GET)
	public void joCaptain(Model model) {
		model.addAttribute("joCaptain", service.joCaptain());
	}
	
	// jList
	@RequestMapping(value = "/joList", method = RequestMethod.GET)
	public void jList(Model model) {
		model.addAttribute("joAll", service.joList());
	}
	
	//joDetail
	@RequestMapping(value = "/jodetail", method = RequestMethod.GET) 
	public String detail(HttpSession session, Model model, @RequestParam("jno") String jno, @RequestParam("jCode") String jCode) {
		String uri = "jo/joDetail";
		
		//업데이트
		if ("A".equals(jCode)) {
			uri = "jo/joUpdate";
		}
		
		model.addAttribute("joOne", service.joDetail(Integer.parseInt(jno)));
		return uri;
	}
	
	// insert Form
	@RequestMapping(value = "joInsert", method = RequestMethod.GET)
	public void joInsert() {	
	} //insert Form
	
	// insert
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model model, JoDTO dto) {
		String uri = "redirect: joList";
		
		if (service.joInsert(dto) > 0) {
			model.addAttribute("message", "조 등록 완료");
		} else {
			uri = "jo/joInsert";
			model.addAttribute("message", "등록 실패. 다시 시도해주세요. ");
		}
		return uri;
	}
	
	//update
	@RequestMapping(value = "/update", method = RequestMethod.POST) //a태그는 GET
	public String update(Model model, JoDTO dto) {
		
		String uri = "redirect: joList"; // 이거 왜 디테일로는 못감????????????????????????????????????????????
		
		model.addAttribute("newJo", dto);
		System.out.println(dto);
		if (service.joUpdate(dto) > 0) {
			model.addAttribute("message", "조 업데이트 완료");
		} else {
			uri = "jo/joUpdate";
			model.addAttribute("message", "조 업데이트 실패. 다시 하세요.");
		}
		return uri;
	}
	
	// delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model,  @RequestParam("jno") String jno) {
		
		String uri = "redirect:joList";
		
		if (service.joDelete(Integer.parseInt(jno)) > 0) {
			model.addAttribute("message", "조 삭제 완료");
		} else {
			model.addAttribute("message", "조 삭제 안됨. 다시 시도하세요.");			
		}
		
		return uri;
	}
	
	
	
	
	
}
