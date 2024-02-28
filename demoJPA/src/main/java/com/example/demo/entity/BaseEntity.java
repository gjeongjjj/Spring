package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

//** BaseEntity
//=> 자료 등록시간, 수정시간 등 자동으로 추가되고 변경되는 값들을 자동으로 처리하기위한 BaseEntity 클래스 
//=> 추상클래스로 작성     
//=> @MappedSuperclass: 테이블로 생성되지않음
//=> @EntityListeners : 엔티티객체의 변화를 감지하는 리스너설정 (AuditingEntityListener.class 가 담당)
//   AuditingEntityListener 를 활성화 시키기 위해서는 
//   DemoJpaApplication.java 에 @EnableJpaAuditing 설정추가해야함.

// 테이블로 생성하는 것이 아니라는 의미. 
@MappedSuperclass
// 실행시에 작동되도록 EntityListener 넣어준다. value값에 리스너 넣어줘야 함. 
@EntityListeners(value = {AuditingEntityListener.class })
// 임의의 시간이 아니라 시스템 시간이기 때문에 GEtter 써야함. 
@Getter 
abstract class BaseEntity {

	@CreatedDate
	@Column(name = "regdate", updatable = false) 
	private LocalDateTime regDate;
	
	@LastModifiedDate
	@Column(name = "moddate" )
	private LocalDateTime modDate; // 같은거지만 수정된 시간을 의미함. 
}





