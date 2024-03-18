package com.gdu.prj03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.prj03.service.BoardService;

import lombok.RequiredArgsConstructor;

//       @Controller  @Service  @Repository
//view - controller - service - dao 

@RequiredArgsConstructor
@Controller     // Controller 에서 사용하는 @Component
public class BoardController {

  /*********************************  DI  *****************************************/
  /*
   * Dependency Injection
   * 
   * 1. 의존 주입
   * 2. Spring Container 에 저장된 빈을 특정 객체에 넣어 주는 것을 의미한다. 
   * 3. 방법
   *  1) 필드 주입
   *  2) 생성자 주입
   *  3) Setter 주입
   *      => 보통 DI(의존성 주입)을 방식에는 필드 주입, Setter 주입, 생성자 주입 3가지의 방법이 있는데 
   *         이중에서 가장 권장하는 의존성 주입은 '생성자 주입' 방식
   * 4. 사용 가능한 annotation
   *  1) @Inject
   *  2) @Resources, @Qualifier
   *  3) @Autowired (대부분 이거 사용)
   */
  
  // 1. 필드 주입
//  @Autowired
//  private BoardService boardService;
  
  
  // 2. 생성자 주입 
  //  1) 생성자의 매개변수로 주입된다.
  //  2) @Autowired 를 생략할 수 있다.
//  private BoardService boardService;
//  public BoardController(BoardService boardService) {
//    super();
//    this.boardService = boardService;
//  }
  
  
  // 3. Setter 주입
  //  1) 메소드의 매개변수로 주입된다.
  //  2) @Autowired 를 생략할 수 없다.
  //  3) 사실 메소드명은 상관이 없다.
//  private BoardService boardService;
//  @Autowired
//  public void setBoardService(BoardService boardService) {
//    this.boardService = boardService;
//  }
  
  
  
  /* === 앞으로 사용할 한 가지 방식 === */
  // final 필드 + 생성자 주입 (lombok의 @RequiredArgsConstructor를 이용해서 매개변수의 null 체크를 수행함)
  /*
   * RequiredArgsConstructor : 클래스에 선언된 final 변수들, 필드들을 매개변수로 하는 생성자를 자동으로 생성해주는 어노테이션
   *                           final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
   */
  
  private final BoardService boardService;
  

  @GetMapping("/board/list.do")
  public String list(Model model) {
    model.addAttribute("boardList", boardService.getBoardList());
    return "board/list";
  }
  
  @GetMapping("/board/detail.do")
  public String detail(int boardNo, Model model) {
    model.addAttribute("board", boardService.getBoardByNo(boardNo));
    return "board/detail";
  }
  
}
