<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연습하기1</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<style>
  .board{
    width: 200px;
    height: 100px; 
    border: 1px solid gray;
    cursor: pointer;
  }
</style>

</head>

<body>

  <div>
    <button type="button" id="btn-list">목록갱신</button>
  </div>
  
  <hr>
  
  <div id="board-list"></div>

  <script>  // 게시글 목록 가져오기
  
    const fnBoardList = () =>{
    	
      $('#btn-list').on('click', (evt)=>{
    	  $.ajax({
    		  /* 요청 */
    		  type: 'GET',   // get 방식
    		  url: '${contextPath}/ajax1/list.do',  // 경로 
    		  /* 응답 */
    		  dataType: 'json',   // json 방식으로
    		  success: (resData)=>{
    			  const boardList = $('#board-list');  // 성공한다면 보드리스트에 응답 데이터 출력해줘
    			  boardList.empty();                   // 일단 비우고 시작
    			  $.each(resData, (i, board)=>{        // 배열로 저장되어있으니 하나씩 꺼내줘
    				  boardList.append('<div class="board"><div class="board-no">' + board.boardNo + '</div><div>' + board.title + '</div><div>' + board.contents + '</div></div>');
    			  })
    		  }
    	  })
      })
      
    }
    
    fnBoardList();  // 호출
  
  </script>
  
  <script>  // 팝업창에 해당 글의 상세내용 띄우기
  
    var detailWindow = null;    // 너비/높이/top-left 초기화
  
    const fnBoardDetail=()=>{
    	
    	$(document).on('click', '.board', (evt) => {    // .board는 이벤트를 통해서 만들어진 동적요소이기 때문에 바꿔줘야한다.
    		
    		const boardNo = $(evt.currentTarget).find('.board-no').text();   // 현재 누른 타겟안의 속성중에 board-no를 찾아서 그 안에 입력된 텍스트를 출력해라
    		                                                                 // 1번 상자를 누르면 1번에 저장된 내용들을 팝업창에 그 내용들을 띄우기
                  	 	//  evt.target;        ----> .board 내부 요소 중 실제로 클릭한 요소
                  	  //  evt.currentTarget; ----> .board 자체
                  	  
                  	  /*
                  	    < target과 currentTarget의 차이점 >
                  	    
                	      핵심은 currentTarget은 이벤트 핸들러가 부착된 것을 가리킨다는 것이다!
                	      즉, target은 부모로부터 이벤트가 위임되어 발생하는 자식의 위치, 내가 클릭한 자식 요소를 반환한다. 
                	      하지만 currentTarget은 이벤트가 부착된 부모의 위치를 반환한다.
                  	  */
    		$.ajax({
    			/* 요청 */ 
    			type: 'GET',                             // get 방식
    		  url: '${contextPath}/ajax1/detail.do',   // 주소
    		  data: 'boardNo=' + boardNo,              // data : 요청 파라미터
    		  /* 응답 */
    		  dataType: 'json',
    		  success: (resData) => {
    			  if(detailWindow === null || detailWindow.closed){      // 디테일창이 null이거나 팝업창들이 닫혀있다면 실행해라
    			    detailWindow = window.open('', '', 'width=300,height=200,top=100,left=100');  // 팝업창의 크기 지정
    			    detailWindow.document.write('<div>' + resData.boardNo + '</div>');            // 1
    			    detailWindow.document.write('<div>' + resData.title + '</div>');              // 제목
    			    detailWindow.document.write('<div>' + resData.contents + '</div>');           // 내용
    			  } else {
    				  alert('기존 창을 닫아주세요.');  // 앞에 열었던 창이 열려있다면 닫고 다시 열어랏
    				  detailWindow.focus();            // 앞서 열려있던 창에 포커스되도록
    			  }
    		  }
    		})
    		
    	})
    	
    }
    
    fnBoardDetail();  //호출
    
  </script>

</body>
</html>