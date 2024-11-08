package com.hk.board.daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.hk.board.dtos.AnswerDto;

public interface IAnsDao {
		//1.글목록 조회하기
		public List<AnswerDto> getAllList(String pnum);
		//2.글 상세조회
		public AnswerDto getBoard(String seq);
		//페이지수 구하기
		public int getPCount();
		//새글 추가하기: insert 문 실행
		public boolean insertBoard(AnswerDto dto);
		//답글추가하기
//		public boolean replyBoard(AnswerDto dto);
		public int replyUpdate(AnswerDto dto);
		public int replyInsert(AnswerDto dto);
		//조회수 올리기:update 문
		public boolean readCount(int seq);
}
