package com.gdu.prj07.aspect;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class MyBeforeAspect {
  
  // PointCut : 언제 동작하는가?
  @Pointcut("execution (* com.gdu.prj07.controller.*Controller.*(..))")
  public void setPointCut() {}
  
  // Advice : 무슨 동작을 하는가?
  @Before("setPointCut()")
  public void myBeforeAdvice(JoinPoint joinPoint) {
    /*
     * Before Advice 메소드 작성 방법
     * 
     * 1. 반환타입 : void
     * 2. 메소드명 : 마음대로
     * 3. 매개변수 : JoinPoint 타입 객체
     */
    
    // 요청 메소드 / 주소 / 파라미터 로그 남기기
    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest(); 
    
    Map<String, String[]> params = request.getParameterMap();    // map 타입으로 파라미터 전달 
    
    String str = "";
    if(params.isEmpty()) {        // 파라미터가 비어있다면 no parameter 출력
      str += "No Parameter";     
    } else {
      for(Entry<String, String[]> entry : params.entrySet()) {            // key랑 value 하나씩 꺼내서 비교하기
        str += entry.getKey() + ":" + Arrays.toString(entry.getValue()) + " ";  
      }
    }
     
    log.info("{} | {}", request.getMethod(), request.getRequestURI());      // [12:28:41.865]  INFO | com.gdu.prj06.aspect.MyBeforeAspect | GET | /prj06/contact/list.do
    log.info("{}", str);
    // 파라미터가 있다면 :  [12:28:40.460]  INFO | com.gdu.prj06.aspect.MyBeforeAspect | name:[gg] mobile:[gg] email:[gg] address:[gg] 
    // 파라미터가 없다면 :  [12:28:41.865]  INFO | com.gdu.prj06.aspect.MyBeforeAspect | No Parameter

  }

}
