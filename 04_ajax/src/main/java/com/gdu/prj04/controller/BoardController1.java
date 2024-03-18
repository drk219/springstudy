package com.gdu.prj04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.prj04.dto.BoardDto;
import com.gdu.prj04.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/ajax1")     //  /ajax1 로 시작하는 모든 요청을 담당하는 컨트롤러
@Controller
@RequiredArgsConstructor
public class BoardController1 {
  
  private final BoardService boardService;
  
  @ResponseBody     // 내가 반환하려는건 'jsp가 아니라 어떤 데이터다' 라고 설정해주는 annotation (비동기 작업에서 꼭 필요한)
  @GetMapping(value="/list.do", produces="application/json")     // produces : 응답 데이터 타입 (Content-Type)
  public List<BoardDto> list() {     // jackson 라이브러리가 List<BoardDto>를 JSON 데이터로 변환해 준다. 
    
    /* Jackson 라이브러리 : Java Object 형태의 값을 데이터 구조를 표현하는 방식인 XML 또는 JSON 형태로 만들어 보낼 때 사용하는 라이브러리 */
    
    return boardService.getBoardList();
  }
  
  
  @ResponseBody
  @GetMapping(value="/detail.do", produces="application/json")
  public BoardDto detail(@RequestParam int boardNo) {
    
    return boardService.getBoardByNo(boardNo);
  }

}
