package com.gdu.mybatis_ex.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.mybatis_ex.common.ActionForward;
import com.gdu.mybatis_ex.dao.StudentDao;
import com.gdu.mybatis_ex.dao.StudentDaoImpl;

public class StudentServiceImpl implements StudentService {
  
  private StudentDao studentDao = StudentDaoImpl.getInstance();

  @Override
  public ActionForward addStudent(HttpServletRequest request) {
    return null;
  }

  @Override
  public ActionForward getStudentList(HttpServletRequest request) {
    return null;
  }

  @Override
  public ActionForward getStudentByNo(HttpServletRequest request) {
    return null;
  }

  @Override
  public ActionForward editStudent(HttpServletRequest request) {
    return null;
  }

  @Override
  public ActionForward modifyStudent(HttpServletRequest request) {
    return null;
  }

  @Override
  public ActionForward removeStudent(HttpServletRequest request) {
    return null;
  }

}
