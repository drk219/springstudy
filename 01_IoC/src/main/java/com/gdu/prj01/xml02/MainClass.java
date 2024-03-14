package com.gdu.prj01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("com/gdu/prj01/xml02/appCtx.xml");
    
    Student student = (Student) ctx.getBean("student");
    
    System.out.println(student.getScores());   // [90, 91, 92]
    System.out.println(student.getContacts()); // [010-1111-2222, 02-1111-3333]
    System.out.println(student.getFriends());  // {동네친구=길동, 운동친구=또치}

    ctx.close();
    
  }

}
