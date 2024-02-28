package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;

// ** JPA CRUD 구현
// => Entity 와 DTO를 용도별로 분리해서 사용
// 	  dtoToEntity()와 entityToDto() 메서드 추가
//    즉, default 메서드로 작성
public interface GuestbookService {
	
	// *** JPA Pageable 을 이용한 Paging 가능.   // 리턴값은 result임. 
	PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO);
	
	
	// => JPA CRUD 구현
	List<Guestbook> SelectList();
	Guestbook selectOne(long gno);
	Long register(GuestbookDTO dto); //insert, update 모두 사용, key를 리턴.  
	// 누구를 리턴했는지 키를 받을 거임. 그니까 Long 타입. 
	void delete(long gno); 
	
	// => dtoToEntity()
	//    insert, update 위해 주로 사용되므로 regDate, modDate 는 제외됨. 
	default Guestbook dtoToEntity(GuestbookDTO dto) {  //메서드가 리턴타입.
		Guestbook entity = Guestbook.builder()
						   .gno(dto.getGno())
						   .title(dto.getTitle())
						   .content(dto.getContent())
						   .writer(dto.getWriter())
						   .build();
		return entity;
	}
	
	// => entityToDto() 
	//    결과 출력시 주로 사용되므로 regDate, modDate 포함됨. 
	default GuestbookDTO entityToDto(Guestbook entity) {
		return GuestbookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
	}
	
	
	
} //interface 
