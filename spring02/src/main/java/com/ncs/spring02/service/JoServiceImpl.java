package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.JoDAO;
import com.ncs.spring02.model.MemberDAO;

//** Service
//=> 요청클래스 와 DAO클래스 사이의 연결(완충지대) 역할
//=> 요청클래스(컨트롤러) 와 DAO클래스 사이에서 변경사항이 생기더라도 서로 영향   받지않도록해주는 역할
// 결합도는 낮추고, 응집도는 높인다

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...

@Service
public class JoServiceImpl implements JoService {
	  
	@Autowired
	JoDAO dao;
	
	// joCaptain
	@Override
	public List<JoDTO> joCaptain() {
		return dao.joCaptain();
	}
	
	// joList
	@Override
	public List<JoDTO> joList() {
		return dao.joList();
	}
		
	// joDetail
	@Override
	public JoDTO joDetail(int jno) {
		return dao.joDetail(jno);
	}
	
	// joInsert
	@Override
	public int joInsert(JoDTO dto) {
		return dao.insert(dto);
	}
	
	// update
	@Override
	public int joUpdate(JoDTO dto) {
		return dao.update(dto);
	}
	
	// delete
	@Override
	public int joDelete(int jno) {
		return dao.delete(jno);
	}
	
	
}
