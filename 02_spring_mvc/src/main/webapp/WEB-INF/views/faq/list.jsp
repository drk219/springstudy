<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 목록</title>
<script>
  function fnAddResult() {
	  let addResult = '${addResult}';  // 값이 안오더라도 빈문자열로 인식하도록 ''를 붙여준다
	  if(addResult !== '' && addResult === '1'){  // 빈문자열이 아니거나 1 일때 
		  alert('정상적으로 등록되었습니다.')
	  }
  }
  fnAddResult();
</script>
</head>
<body>

</body>
</html>