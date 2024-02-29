package com.example.demo.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository;
	
	// ** jno 별 Member 출력
	// => JPARepository Method Naming 규약 
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}
	
	// 2번
	@Override
	public void updatePassword1(String id, String password) {
		repository.updatePassword1(id, password);
	}
	
	// ** Join
	@Override
	public List<MemberDTO> findMemberJoin() {
		return repository.findMemberJoin();
	}
	
	
	
	
	// ** selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}
	
	// ** selectOne
	@Override
	public Member selectOne(String id) {
		
		Optional<Member> result = repository.findById(id);
		if (result.isPresent()) return result.get(); 	//존재한다면 
		else return null;
		
	}
	
	// ** insert , update
	// 받는 타입을 Member로. repository에서 save메서드
	@Override
	public Member save (Member entity) {
		return repository.save(entity);
	}

	// ** passwordUpdate
	@Override
	public Member pwUpdate(Member entity) {
		return null;
	}
	
	// ** delete
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}
} // class