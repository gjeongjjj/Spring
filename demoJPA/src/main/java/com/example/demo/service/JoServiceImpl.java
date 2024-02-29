package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Jo;
import com.example.demo.repository.JoRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class JoServiceImpl implements JoService {
	 
	private final JoRepository repository;
	
	// joCaptain
	@Override
	public List<Jo> joCaptain() {
		return repository.findAll();
	}
	
	// joDetail
	@Override
	public Jo joDetail(int jno) {
		Optional<Jo> result = repository.findById(jno);
		if (result.isPresent()) return result.get();
		else return null;
	}
	
	// ** insert, update
	@Override
	public Jo save(Jo entity) {
		return repository.save(entity);
	}
	
	
	// joUpdate
	@Override
	public void joDelete(int jno) {
		repository.deleteById(jno);
	}
	// void로 바꿨던 이유?
	
}
