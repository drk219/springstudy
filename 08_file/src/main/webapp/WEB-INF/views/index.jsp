<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
  
  <div>
    <form action="${contextPath}/upload1.do"
          method="post"
          enctype="multipart/form-data">
      <div>
        <input type="file" name="files" class="files" accept="image/*" multiple>
      </div>
      <div>
        <input type="text" name="writer" placeholder="작성자">  <!-- getParameter -->
      </div>
      <div>
        <button type="submit">전송</button>
      </div>
    </form>
  </div>
  
  <h3>첨부 파일 목록</h3>
  <div id="file-list"></div>
  
  <hr>
  
  <div>
    <input type="file" id="input-files" class="files" multiple>
  </div>
  <div>
    <input type="text" id="input-writer" placeholder="작성자"> 
  </div>
  <div>
    <button type="button" id="btn-upload">전송</button>
  </div>
  
  <script>
  
    const fnFileCheck = () => {
    
    	$('.files').on('change', (evt)=>{  // change -> 파일이 선택된 상태
    		// 첨부 파일 크기 설정
    		const limitPerSize = 1024 * 1024 * 10;    // 10MB
    		const limitTotalSize = 1024 * 1024 * 100  // 100MB
    		let totalSize = 0;
    		// evt.target.files 은 여러 파일은 가지고 있는 배열 
    		const files = evt.target.files;
    		const fileList = document.getElementById('file-list');
    		
    		// 초기화
    		fileList.innerHTML = '';
    		
    		for(let i = 0; i < files.length; i++){
    			
    			// 첨부 파일 1개 최대 허용 크기
    			if(files[i].size > limitPerSize){
    				alert('각 첨부 파일의 최대 크기는 10MB 입니다.');
    				evt.target.value = '';  // 빈문자열로 파일 지우기
    				fileList.innerHTML = '';
    				return;
    			}
    			totalSize += files[i].size;
    			
    			// 첨부된 파일 크기 합 최대 허용 크기
    			if(totalSize > limitTotalSize){
    				alert('전체 첨부 파일의 최대 크기는 100MB 입니다');
    				evt.target.value = '';
    				fileList.innerHTML = '';
    				return;
    			}
    			
    			// 첨부된 파일 목록으로 하나씩 표시
    			fileList.innerHTML += '<div>' + files[i].name + '</div>';
    		}
    	})
    }
    
    // 파일 저장 성공 유무
    const fnAfterInsertCheck = () => {
    	const insertCount = '${insertCount}';
    	if(insertCount != ''){
    		if(insertCount === '1'){
    			alert('저장되었습니다.');
    		} else {
    			alert('저장에 실패했습니다.');
    		}
    	}
    }
    
    // 파일 첨부 비동기적 업로드 
    const fnAsyncUpload = ()=>{
    	const inputFiles = document.getElementById('input-files');
    	const inputWriter = document.getElementById('input-writer');
    	
    	// 새 FormData 객체를 생성하여 formData에 할당
    	// FormData 객체는 파일 및 키/값 쌍을 서버로 보내기 위해 사용됨
    	let formData = new FormData();
    	
    	// 파일 입력(input)에서 선택된 파일들을 하나씩 반복하면서 FormData에 추가
    	for(let i = 0; i < inputFiles.files.length; i++){
    		formData.append('files', inputFiles.files[i]);
    	}
    	formData.append('writer', inputWriter);
    	
    	// fetch 함수 이용해서 서버로 데이터 전송
    	fetch('${contextPath}/upload2.do', {  // 전송할 경로
    		method: 'POST', 
    		body: formData
    	}).then(response=>response.json())
    	  .then(resData=>{      // resData ={"success": 1}, {"success": 0}
    		  
    	    // 응답 데이터 'success' 속성이 1인 경우에는 성공 메시지를 표시
    	    if(resData.success === 1){
    			  alert('저장되었습니다.');
    		  } else {
    			  alert('실패하였습니다.');
    		  }
    	  })
    }
    
    // 파일 첨부 비동기적 업로드
    const fnAsyncUpload2 = ()=>{
      const inputFiles = document.getElementById('input-files');
      const inputWriter = document.getElementById('input-writer');
      let formData = new FormData();
      for(let i = 0; i < inputFiles.files.length; i++){
        formData.append('files', inputFiles.files[i]);
      }
      formData.append('writer', inputWriter);
      
      // ajax 처리
      $.ajax({
        type: 'POST',
        url: '${contextPath}/upload2.do',
        contentType: false,  // content-type header를 생성하지 않도록 설정
        data: formData,      // FormData 객체를 서버로 전달
        processData: false,  // 전달되는 데이터가 객체인 경우 객체를 {property: value} 형식의 문자열로 자동으로 변환해서 전달하는데 이를 방지하는 옵션
        dataType: 'json'
      }).done(resData=>{
          if(resData.success === 1){
            alert('저장되었습니다.');
          } else {
            alert('저장실패했습니다.');
          }
        })
      }
    
    // 호출
    fnFileCheck();
    fnAfterInsertCheck();
    document.getElementById('btn-upload').addEventListener('click', fnAsyncUpload);
    
  </script>
  
</body>
</html>