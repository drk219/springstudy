<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 상세 조회</title>
</head>
<body>

  <!-- 상세조회 학번 평균 학점 수정 불가능 -->
  
  <div>
    <h1>학생 상세 조회</h1>
  </div>
  
  <form id="frm-detail"
        action="${contextPath}/student/modify.jsp"
        method="get">
    <div>
      <label for="studentNo">학번</label>
      <input type="text" name="studentNo" id="studentNo" value="${student.studentNo}">
    </div>
    <div>
      <label for="">이름</label>
      <input type="text" name="name" id="name" value="${student.name}">
    </div>
    <div>
      <label for="">국어</label>
      <input type="text" name="" id="" value="${student.korean}">
    </div>
    <div>
      <label for="">영어</label>
      <input type="text" name="" id="" value="${student.english}">
    </div>
    <div>
      <label for="math">수학</label>
      <input type="text" name="math" id="math" value="${student.math}">
    </div>
    <div>
      <label for="average">평균</label>
      <input type="text" name="average" id="average" value="" readonly="readonly">
    </div>
    <div>
      <label for="grade">학점</label>
      <input type="text" name="grade" id="grade" value="" readonly="readonly">
    </div>
  </form>
  
  <hr>
  
  <div>
    <button type="submit" name="modify" id="modify">수정하기</button>
    <button type="submit" name="list" id="list">목록보기</button>
  </div>
  

</body>
</html>