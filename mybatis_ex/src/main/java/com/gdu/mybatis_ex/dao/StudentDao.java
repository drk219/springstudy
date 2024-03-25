package com.gdu.mybatis_ex.dao;

import java.util.List;
import java.util.Map;

import com.gdu.mybatis_ex.dto.StudentDto;

public interface StudentDao {

  int insertStudent(StudentDto student);
  int updateStudent(StudentDto student);
  int deleteStudent(int studentNo);
  List<StudentDto> selectStudentList(Map<String, Object> params);  
  int getStudentCount();
  StudentDto selectStudentByNo(int studentNo);  // 학번으로 해당 학생 상세보기
  
}
