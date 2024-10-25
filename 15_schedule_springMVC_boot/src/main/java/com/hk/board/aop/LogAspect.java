package com.hk.board.aop;

import org.slf4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	//로그 관리를 수행할 객체 생성
	Logger logger=LoggerFactory.getLogger(getClass());
	//execution():메서드단위로 설정한다.
	//within():클래스단위로 설정한다.-com.hk.board.controller.*
	@Pointcut(value="within(com.hk.board.controller.*)")
	public void controller() {}
	//before
	@Before(value="controller()")
	public void before(JoinPoint join) {
		logger.info("before메서드실행:{}",join.getSignature().getName());
	}
	@AfterReturning(pointcut="controller()",returning="returnVal")
	//afterReturning
	//returning 속성:메서드에 정의한 파라미터에 반환값을 보내줌
	public void afterReturning(JoinPoint join,Object returnVal) {
		logger.info("afterReturning:{}",join.getSignature().getName());
		if(returnVal==null) {
			return;
		}else {
			logger.info("리턴값:{}",returnVal);
		}
	}
	//afterThrowing
	@AfterThrowing(pointcut="controller()",throwing="e")
	public void afterThrowing(JoinPoint join,Exception e) {
		logger.info("afterThrowing메서드실행:{}",join.getSignature().getName());
		logger.info("오류내용:{}",e.getMessage());
	}
	//Around: 메서드 실행 전과 후 모두 적용
}
