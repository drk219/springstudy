<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규학생등록</title>
</head>
<body>

  <div>
    <h1>신규학생등록 화면</h1>
  </div>
  
  <form action="${contextPath}/student/register.brd"
        method="post"
        id="frm-register">
    <div>
      <label for="name">이름</label>
      <input type="text" name="name" id="name">
    </div>
    <div>
      <label for="korean">국어</label>
      <input type="text" name="kor" id="korean">
    </div>
    <div>
      <label for="english">영어</label>
      <input type="text" name="english" id="english">
    </div>
    <div>
      <label for="math">수학</label>
      <input type="text" name="math" id="math">
    </div>
  </form>
  
  <hr>
  
  <div>
    <button type="submit" name="register" id="register">작성완료</button>
    <button type="reset">다시작성</button>
    <button type="submit" name="list" id="list">목록보기</button>
  </div>

</body>
</html>