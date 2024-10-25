package com.hk.ans.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
	private int seq;
	private String id;
	private String title;
	private String content;
	private Date regDate;
	private int refer;
	private int step;
	private int depth;
	private String readCount;
	private String delflag;
	
	public AnswerDto(String id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public AnswerDto(int seq, String id, String title, String content) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
}
