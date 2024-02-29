package com.example.demo.service;

import java.util.List; 

import com.example.demo.entity.Jo; 


public interface JoService {

	// joCaptain
	List<Jo> joCaptain();

	// joDetail
	Jo joDetail(int jno);

	// JoInsert, JoUpdate
	Jo save(Jo entity);
	
//	// joInsert
//	int joInsert(Jo entity);
//
//	// update
//	int joUpdate(Jo entity);

	// delete
	void joDelete(int jno);

}