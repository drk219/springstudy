package com.gdu.mybatis_ex.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.gdu.mybatis_ex.common.ActionForward;
import com.gdu.mybatis_ex.dao.StudentDao;
import com.gdu.mybatis_ex.dao.StudentDaoImpl;
import com.gdu.mybatis_ex.dto.StudentDto;

public class StudentServiceImpl implements StudentService {
  
  private StudentDao studentDao = StudentDaoImpl.getInstance();

  @Override
  public ActionForward addStudent(HttpServletRequest request) {
    
    int studentNo = Integer.parseInt(request.getParameter("studentNo"));
    String name = request.getParameter("name");
    int korean = Integer.parseInt(request.getParameter("korean"));
    int english = Integer.parseInt(request.getParameter("english"));
    int math = Integer.parseInt(request.getParameter("math"));
    StudentDto student = StudentDto.builder()
                             .studentNo(studentNo)
                             .name(name)
                             .korean(korean)
                             .english(english)
                             .math(math)
                             .build();
    int insertCount = studentDao.insertStudent(student);
    
    // redirect 경로는 URLMapping 으로 작성한다.
    String view = null;
    if(insertCount == 1) {
      view = request.getContextPath() + "/student/list.do"; 
    } else if(insertCount == 0) {
      view = request.getContextPath() + "/main.do";
    }
    
    // INSERT 이후 이동은 redirect
    return new ActionForward(view, true);  // -> redirect
  }

  @Override
  public ActionForward getStudentList(HttpServletRequest request) {

    int total = studentDao.getStudentCount();
    Map<String, Object> params = Map.of(); 
    List<StudentDto> studentList = studentDao.selectStudentList(params);
    request.setAttribute("total", total);
    request.setAttribute("stduentList", studentList);
    return new ActionForward("/student/list.jsp", false);
  }

  @Override
  public ActionForward getStudentByNo(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("studentNo"));
    int studentNo = Integer.parseInt(opt.orElse("0"));
    StudentDto student = studentDao.selectStudentByNo(studentNo);
    String view = null;
    if(student != null) {
      view = "/student/detail.jsp";
      request.setAttribute("student", student);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);  // false => forward 는 .jsp 로 이동
  }

  @Override
  public ActionForward modifyStudent(HttpServletRequest request) {
    int studentNo = Integer.parseInt(request.getParameter("studentNo"));
    int korean = Integer.parseInt(request.getParameter("korean"));
    int english = Integer.parseInt(request.getParameter("english"));
    int math = Integer.parseInt(request.getParameter("math"));
    StudentDto student = StudentDto.builder()
                             .korean(korean)
                             .english(english)
                             .math(math)
                             .build();
    int updateCount = studentDao.updateStudent(student);
    String view = null;
    if(updateCount == 0) {
      view = request.getContextPath() + "/main.do";  
    } else {
      view = request.getContextPath() + "/student/detail.do?studentNo=" + studentNo;
    }
    return new ActionForward(view, true);   // redirect
  }

  @Override
  public ActionForward removeStudent(HttpServletRequest request) {
    String param = request.getParameter("studentNo");
    int studentNo = 0;
    if(!param.isEmpty()) {
      studentNo = Integer.parseInt(param);
    }
    int deleteCount = studentDao.deleteStudent(studentNo);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.do";   
    } else {
      view = request.getContextPath() + "/student/list.do";
    }
    return new ActionForward(view, true);  // redirect
  }

}
