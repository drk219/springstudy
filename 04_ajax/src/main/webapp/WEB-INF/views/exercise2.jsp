<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연습하기2</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

<!-- 부트 스트랩 사용을 위한 cdn -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<style>
  .board-wrap{
    width: 320px;
    margin: 10px auto;
    display: flex;
    flex-wrap: wrap;
  }
  .board{
    width: 150px;
    height: 150px;
    border: 1px solid gray;
    cursor: pointer;
    margin: 0 5px 5px 0;
  }
  .board:hover {
    background-color: beige;
  }
  
  /* 모달창을 여는 버튼을 보녀 줄 필요가 없어서 화면에서 치우기 */
  #btn-modal-wrap{
    position: relative;
  }
  #btn-modal{
    position: absolute;
    top: -9999px;
  }
</style>

</head>

<body>

  <div>
    <button type="button" class="btn btn-primary" onclick="fnBoardList()">목록갱신</button>
  </div>
  
  <hr>
  
  <div id="board-list"></div>
  
  <!-- Modal 창을 여는 버튼 영역 -->
  <div id="btn-modal-wrap"></div>
  
  <!-- Vertically centered scrollable modal ---------------------------------------------------------->
  <div class="modal" id="my-modal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">  
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p>Modal body text goes here.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
  <!---------------------------------------------------------------------------------------------------->
  
  
  <script>  // 버튼 클릭하면 화면에 목록 띄우기
    
    const fnBoardList = ( )=>{
    	
    	$.ajax({
    		/* 요청 */
    		type: 'GET',
    		url: '${contextPath}/ajax2/list.do',
    		/* 응답 */
    		dataType: 'json'
    	}).done((resData)=>{
    		const boardList = $('#board-list');
    		boardList.empty();
    		let result = '<div class="board-wrap">';
    		$.each(resData, (i, board)=>{   // 인덱스와 board의 값이 하나씩 넘어가는 (resData는 JSON 배열이며, 각 요소는 board 변수로 전달)
    			result += '<div class="board" data-board-no="' + board.boardNo + '"><div>' + board.boardNo + '</div><div>' + board.title + '</div><div>' + board.contents + '</div></div>';
    		})
    		result += '</div>';
    		boardList.append(result);
    	})
    	
    }
  
  </script>
  
  <script>  // bootstrap의 기능을 이용해 modal 창 열어서 상세내용 보이기
    
    const fnBoardDetail = ()=>{
      
      $(document).on('click', '.board', (evt)=>{
    	  
        const boardNo = evt.currentTarget.dataset.boardNo;     // $(evt.currentTarget).data('boardNo')
        $.ajax({
          /* 요청 */
          type: 'GET',
          url: '${contextPath}/ajax2/detail.do',
          data: 'boardNo=' + boardNo,    // 요청한 게시번호의 상세보기 가져오기
          /* 응답 */
          dataType: 'json'
        }).done((resData)=>{
        	
          // 모달창에 내용 넣기
          const myModal = $('#my-modal');
          myModal.find('.modal-title').html('<h1>' + resData.boardNo + '번 게시글</h1>');
          myModal.find('.modal-body').html('<div>제목 : ' + resData.title + '</div><div>' + resData.contents + '</div>');
          
          // 모달창을 여는 버튼을 만들어서 넣고 클릭시키기
          $('#btn-modal-wrap').empty();
          const btn = '<button type="button" id="btn-modal" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#my-modal">상세</button>';
          $('#btn-modal-wrap').append(btn);
          $('#btn-modal').trigger('click');   // 강제 실행
          
        })
      })
      
    }
    
    fnBoardDetail();
    
  </script>

</body>
</html>