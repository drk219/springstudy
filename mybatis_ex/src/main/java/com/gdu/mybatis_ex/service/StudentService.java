package com.gdu.mybatis_ex.service;

import javax.servlet.http.HttpServletRequest;

import com.gdu.mybatis_ex.common.ActionForward;

public interface StudentService {

  ActionForward addStudent(HttpServletRequest request);          // 추가 하기 
  ActionForward getStudentList(HttpServletRequest request);      // 목록 보기
  ActionForward getStudentByNo(HttpServletRequest request);      // 학생 번호 선택해서 상세 보기
  ActionForward editStudent(HttpServletRequest request);         // 편집화면으로 넘어가는
  ActionForward modifyStudent(HttpServletRequest request);       // 데이터 베이스를 수정하는 서비스 
  ActionForward removeStudent(HttpServletRequest request);       // 학생 한명 삭제 하기
  
}
