<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
</head>
<body>

  <div>
    <h1>학생 관리</h1>
  </div>
  <div>
    <a href="${contextPath}/student/write.do">
    <button type="submit" id="register" name="register">신규학생등록</button>
    </a>
  </div>
  
  <hr>
  
  <div>
    <span>평균</span>
    <input type="text" placeholder="begin" id="begin" name="begin" size="5">
    <span> ~ </span>
    <input type="text" placeholder="end" id="end" name="end" size="5">
    <button type="submit" name="search" id="search">조회</button>
    <button type="submit" name="search-all" id="search-all">전체조회</button>
  </div>
  
  <hr>
  
  <table border="1">
    <caption>전체 학생 ${getStudentCount}명</caption>
    <tr>
      <td>${studentNo}</td>
      <td>${name}</td>
      <td>${korean}</td>
      <td>${english}</td>
      <td>${math}</td>
      <td>평균</td>
      <td>학점</td>
      <td></td>
    </tr>
    <tr>
      <td colspan="8">등록된 학생이 없습니다.</td>
    </tr>
    <tr>
      <td colspan="5">전체평균</td>
      <td>0.00</td>
      <td colspan="2"></td>
    </tr>
  </table>

</body>
</html>