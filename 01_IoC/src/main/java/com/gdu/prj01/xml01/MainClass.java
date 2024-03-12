package com.gdu.prj01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
  
  public static void method1() {

    // appCtx.xml 읽기
    // GenericXmlApplicationContext 클래스는 xml 파일에 정의된 설정 정보를 읽어와서 객체를 생성하고, 각각의 객체를 연결한 뒤에 내부적으로 보관
    // AbstractApplicationContext 클래스는 prepareBeanFactory 메서드를 통해 다양한 초기화 작업을 진행
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml01/appCtx.xml");
    
    // appCtx.xml 에 등록한 빈(bean) 가져오기
    Calculator calculator = ctx.getBean("calculator", Calculator.class);
    
    // 가져온 bean 사용하기
    calculator.add(10, 20);
    calculator.sub(10, 5);
    calculator.mul(10, 3);
    calculator.div(10, 2);
    
    // appCtx.xml 닫기
    ctx.close();
    
  }
  
  
  public static void method2() {
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml01/appCtx.xml");
    
    Computer computer1 = ctx.getBean("computer1", Computer.class);
    System.out.println(computer1.getModel());
    System.out.println(computer1.getPrice());
    computer1.getCalculator().add(5, 2);
    computer1.getCalculator().sub(5, 2);
    computer1.getCalculator().mul(5, 2);
    computer1.getCalculator().div(5, 2);
    
    ctx.close();
  
  }
  
  
  public static void method3() {
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml01/appCtx.xml");
    
    Computer computer2 = ctx.getBean("computer2", Computer.class);
    System.out.println(computer2.getModel());
    System.out.println(computer2.getPrice());
    computer2.getCalculator().add(5, 2);
    computer2.getCalculator().sub(5, 2);
    computer2.getCalculator().mul(5, 2);
    computer2.getCalculator().div(5, 2);
    
    ctx.close();
  }
  

  public static void main(String[] args) {
    method1();
    method2();
    method3();
  
  }

}
