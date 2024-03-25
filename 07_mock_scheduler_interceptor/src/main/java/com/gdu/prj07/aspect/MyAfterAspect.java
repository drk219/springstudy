package com.gdu.prj07.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class MyAfterAspect {

  // PointCut : 언제 동작하는가?
  @Pointcut("execution (* com.gdu.prj07.controller.*Controller.*(..))")
  public void setPointCut() {}
  
  // Advice : 무슨 동작을 하는가?
  @After("setPointCut()")
  public void myAfterAdvice(JoinPoint joinPoint) {
    /*
     * After Advice 메소드 작성 방법
     * 
     * 1. 반환타입 : void
     * 2. 메소드명 : 마음대로
     * 3. 매개변수 : JoinPoint 타입 객체
     */
    
    log.info("{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
    // [12:28:41.866]  INFO | com.gdu.prj06.aspect.MyAfterAspect | 2024-03-20 12:28:41:866

  }
 
}
