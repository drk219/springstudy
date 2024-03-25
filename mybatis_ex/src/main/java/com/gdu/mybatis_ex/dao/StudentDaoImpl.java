package com.gdu.mybatis_ex.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.mybatis_ex.dto.StudentDto;

public class StudentDaoImpl implements StudentDao {
  
  private SqlSessionFactory factory = null;
  
  // SingletonPattern 
  private static StudentDao studentDao = new StudentDaoImpl();
  private StudentDaoImpl() {
    
    // mybatis-config.xml 파일을 이용한 SqlSessionFactory 객체 생성
    try {
      String resource = "com/gdu/mybatis_ex/config/mybatis-config.xml";
      InputStream in = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(in);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static StudentDao getInstance() {    // 프라이빗 처리한 BoardDaoImpl을 다른 곳에서 사용할 수 있게 getInstance으로 따로 만들어준다
    return studentDao;
  }

  @Override
  public int insertStudent(StudentDto student) {
    SqlSession sqlSession = factory.openSession(false);  // autoCommit을 하지 않는다. 내가 수동으로 하겠다.
    int insertCount = sqlSession.insert("com.gdu.mybatis_ex.dao.student_t.insertBoard", student);
    if(insertCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return insertCount;
  }

  @Override
  public int updateStudent(StudentDto student) {
    SqlSession sqlSession = factory.openSession(false);
    int updateCount= sqlSession.update("com.gdu.mybatis_ex.dao.student_t.updateBoard", student);
    if(updateCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return updateCount;
  }

  @Override
  public int deleteStudent(int studentNo) {
    SqlSession sqlSession = factory.openSession(false);
    int deleteCount = sqlSession.delete("com.gdu.mybatis_ex.dao.student_t.deleteBoard", studentNo);
    if(deleteCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return deleteCount;
  }

  @Override
  public List<StudentDto> selectStudentList(Map<String, Object> params) {
    SqlSession sqlSession = factory.openSession();
    List<StudentDto> studentList = sqlSession.selectList("com.gdu.mybatis_ex.dto.StudentDto.student.selectStudentList", params);
    sqlSession.close();
    return studentList;
  }

  @Override
  public int getStudentCount() {
    SqlSession sqlSession = factory.openSession();
    int total = sqlSession.selectOne("com.gdu.mybatis_ex.dao.student_t.getStudentCount");
    sqlSession.close();
    return total;
  }

  @Override
  public StudentDto selectStudentByNo(int studentNo) {
    SqlSession sqlSession = factory.openSession();
    StudentDto student = sqlSession.selectOne("com.gdu.mybatis_ex.dao.selectStduentByNo", studentNo);
    sqlSession.close();
    return student;
  }

}
