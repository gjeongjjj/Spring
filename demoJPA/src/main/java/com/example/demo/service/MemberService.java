package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	
	// ** selectList
	List<Member> selectList();

	// ** selectOne
	Member selectOne(String id);

	// ** insert,  update
	Member save(Member entity);
	
	// ** passwordUpdate
	Member pwUpdate(Member entity);

	// ** delete
	void deleteById(String id);
	
	// 1) JPARepository Method 규약 
	//    => jno별 Member 출력
	List<Member> findByJno(int jno);
	
	// 2) @Query 를 이용한 직접쿼리 선언
	// => password Update에 적용
	void updatePassword1(String id, String password);
	
	// ** Join
	List<MemberDTO> findMemberJoin();
	
	
	

}