<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>블로그 상세</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>

<body>

  <div>${blog.blogNo}번 블로그</div>
  <div>${blog.title}</div>
  
  <form action="${contextPath}/blog/add.do" method="post">
    <div>
      <label for="blogNo">블로그 번호</label>
      <input type="text" id="blogNo" name="blogNo">
    </div>
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title">
    </div>
    <div>
      <button type="submit">전송</button>
    </div>
  </form>

</body>
</html>