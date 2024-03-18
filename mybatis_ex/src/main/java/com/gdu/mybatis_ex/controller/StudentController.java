package com.gdu.mybatis_ex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.mybatis_ex.common.ActionForward;
import com.gdu.mybatis_ex.service.StudentService;
import com.gdu.mybatis_ex.service.StudentServiceImpl;


@WebServlet("*.do")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  private StudentService studentService = new StudentServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	   // 요청 주소 확인
    String requestURI = request.getRequestURI();     /* http://localhost:8080/dbcp/board/list.brd */    // 첫화면은 목록화면 보기
    String contextPath = request.getContextPath();   /* /dbcp */
    String urlMapping = requestURI.substring(requestURI.indexOf(contextPath) + contextPath.length());
	  
    ActionForward actionForward = null;

    // 요청 주소에 따른 서비스 메소드 호출
    switch (urlMapping) {
      case "/student/list.do": 
        actionForward = studentService.getStudentList(request);
        break;
      case "/student/write.do":
        actionForward = new ActionForward("/student/write.jsp", false);  // forward
        break;
      case "/student/list.do":
        actionForward = studentService.addStudent(request);
        break;
      case "/main.do":
        actionForward = new ActionForward("/index.jsp", false);  // forward
        break;
      case "/student/detail.do":
        actionForward = studentService.getStudentByNo(request);
        break;
      case "/student/edit.do":
        actionForward = studentService.editStudent(request);
        break;
      case "/student/modify.do":
        actionForward = studentService.modifyStudent(request);
        break;
      case "/student/remove.do":
        actionForward = studentService.removeStudent(request);
        break;
    }
    
    // 어디로 어떻게 이동하는지 결정
    if(actionForward != null) {
      if(actionForward.isRedirect()) {
        response.sendRedirect(actionForward.getView());  // redirect
      } else {
        request.getRequestDispatcher(actionForward.getView()).forward(request, response);  // forward
      }
    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
