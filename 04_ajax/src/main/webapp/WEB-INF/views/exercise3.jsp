<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연습하기3</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

  <div>
    <button type="button" onclick="fnBoardList()">목록갱신</button>
  </div>
  
  <hr>
  
  <div id="board-list"></div>

  <script>
     
    const fnBoardList = ( )=>{
    	
    	const options = {   // AJAX 요청 옵션을 설정합니다. 여기서는 GET 방식으로 요청하고, 캐시를 사용하지 않도록 설정되어 있습니다.
    		method: 'GET',
    		cache: 'no-cache'
    	} 
    	
    	//const myPromise = fetch('${contextPath}/ajax3/list.do', options);    
    	//myPromise.then()
    	
    	fetch('${contextPath}/ajax3/list.do', options)
    	   .then(response=>response.json())  // 응답 데이터를 JSON 형식으로 파싱
    	   .then(resData=>{                  // JSON으로 파싱된 응답 데이터를 받아와 처리
    		   const boardList = document.getElementById('board-list');
    		   boardList.innerHTML = '';       // 내용 초기화
    			 let result = '<div class="board-wrap">';
    		   resData.forEach(board=>{        // resData는 JSON 배열이며, 각 요소는 board 변수로 전달
    			   result += '<div class="board" data-board-no="' + board.boardNo + '"><div>' + board.boardNo + '</div><div>' + board.title + '</div><div>' + board.contents + '</div></div>';
    		   })
    		   result += '</div>';
    		   boardList.innerHTML = result;
    		   fnBoardDetail();
    	   })
    } 
  
  </script>
  
  <script>
    
    const fnBoardDetail = ()=>{
    	
        const boardList = document.getElementById('board-list');
    	for(let i = 0; i < boardList.length; i++){
    		boardList[i].addEventListener('click', (evt)=>{
    			const boardNo = evt.currentTarget.dataset.boardNo;
    			fetch('${contextPath}/ajax3/detail.do?boardNo=' + boardNo, {method: 'GET', cache: 'no-cache'})
    			.then(response=>response.json())
    			.then(resData=>{
    				console.log(resData);
    			})
    		})
    	}
    	
    }
    
  </script>

</body>
</html>