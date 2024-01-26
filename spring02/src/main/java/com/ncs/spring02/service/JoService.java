package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.model.JoDAO;

@Service
public class JoService {
	  
	@Autowired
	JoDAO dao;
	
	// joCaptain
	public List<JoDTO> joCaptain() {
		return dao.joCaptain();
	}
	
	// joList
	public List<JoDTO> joList() {
		return dao.joList();
	}
	
	// joDetail
	public JoDTO joDetail(int jno) {
		return dao.joDetail(jno);
	}
	
	// joInsert
	public int joInsert(JoDTO dto) {
		return dao.insert(dto);
	}
	
	// update
	public int joUpdate(JoDTO dto) {
		return dao.update(dto);
	}
	
	// delete
	public int joDelete(int jno) {
		return dao.delete(jno);
	}
	
	
}
