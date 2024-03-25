package com.gdu.prj06.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
/*
 * Spring Container
 * 
 * <bean>     : 이미 만들어진 클래스의 객체 생성할 때 
 * @Bean      : 이미 만들어진 클래스의 객체 생성할 때
 * @Component : 내가 만드는 클래스의 객체 생성할 때
 */
public class MyAroundAspect {
  
  // PointCut : 언제 동작하는가?
  @Pointcut("execution (* com.gdu.prj06.controller.*Controller.*(..))")
          // 실행하라  모든타입     패키지        모든컨트롤러 모든메소드
  public void setPointCut() {}
  
  // Advice : 무슨 동작을 하는가?
  @Around("setPointCut()")  
  public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    /*
     * Around Advice 메소드 작성 방법
     * 
     * 1. 반환타입 : Object
     * 2. 메소드명 : 마음대로
     * 3. 매개변수 : ProceedingJoinPoint 타입 객체
     */
    
    // 이쪽에 넣으면 동작 이전 (@Before 이전)
    log.info("{}", "-".repeat(80));         // [12:28:40.458]  INFO | com.gdu.prj06.aspect.MyAroundAspect | ------------------------------------------------ 
    
    Object obj = proceedingJoinPoint.proceed();   // advice 가 시작된 시점
    
    // 이쪽에 넣으면 동작 이후 (@After 이후)
    log.info("{}\n\n", "-".repeat(80));     // [12:28:41.863]  INFO | com.gdu.prj06.aspect.MyAroundAspect | -------------------------------------------------
    
    return obj;
    
  }
  
}
