package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private int seq;
	private String id;
	private String title;
	private String content;
	private String regdate;
	// sql 에서는 날짜 시간이지만 여기서는 그냥 String
	private int cnt;
	private int root;
	private int step;
	private int indent;
	
} // class
