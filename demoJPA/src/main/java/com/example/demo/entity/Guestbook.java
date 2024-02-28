package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// import javax.persistence.Table;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
// @Table(name = "guestbook") -> 클래스명과 동일한 경우 생략가능. 
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor // 생성자가 필요하니까. 
public class Guestbook extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gno; // Auto_increment
	
	@Column(length = 100, nullable = false) //name 속성 안줘도 됨. 이름 같으니가.  
	private String title;
	
	@Column(length = 2000, nullable = false)
	private String content;
	
	@Column(length = 50, nullable = false)
	private String writer;
	
	public void changeTitle(String title) {
		this.title = title;
	}
	public void changeContent(String content) {
		this.content = content;
	}
	
}