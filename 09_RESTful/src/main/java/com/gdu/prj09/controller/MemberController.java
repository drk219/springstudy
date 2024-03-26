package com.gdu.prj09.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.gdu.prj09.service.MemberService;

import lombok.RequiredArgsConstructor;

/*
 * RESTful
 * 
 * 1. Representational State Transfer
 * 2. 요청 주소를 작성하는 한 방식이다.
 * 3. 요청 파라미터를 ? 뒤에 추가하는 Query String 방식을 사용하지 않는다. 
 * 4. 요청 파라미터를 주소에 포함하는 Path Variable 방식을 사용하거나, 요청 본문에 포함하는 방식을 사용한다. 
 * 5. 요청의 구분을 "주소 + 메소드" 조합으로 구성한다.
 * 6. CRUD 요청 예시
 *                  |          <URL>                |   <Method>
 *            ------|-------------------------------|--------------            
 *    1) 목록       |   /members                    |     GET
 *                  |   /members/page/1             |
 *                  |   /members/page/1/display/20  |
 *    2) 상세       |   /members/1                  |     GET
 *    3) 삽입       |   /members                    |     POST
 *    4) 수정       |   /members                    |     PUT
 *    5) 삭제(단일) |   /members/1                  |     DELETE
 *    6) 삭제(복수) |   /members/1,2,3              |     DELETE
 */    

@RequiredArgsConstructor
@Controller
public class MemberController {
  
  private final MemberService memberService;
  
  @GetMapping("/admin/member.do")
  public void adminMember() {
    // 반환타입이 void 인 경우 주소를 JSP 경로로 인식한다.
    // /admin/member.do =====> /WEB-INF/views/admin/member.jsp
  }
  
  @PostMapping(value="/members", produces="application/json")
  // member 등록하는 메소드 (스트링 타입도 int타입도 있기 때문에 Map으로 가져오기)
  public ResponseEntity<Map<String, Object>> registerMember(@RequestBody Map<String, Object> map
                                                          , HttpServletResponse response) {
    return memberService.registerMember(map, response);
  }
  
  @GetMapping(value="/members/page/{page}/display/{display}", produces="application/json")
  // 1 페이지마다 20 member를 표시하는 메소드 
  public ResponseEntity<Map<String, Object>> getMembers(@PathVariable(value="page", required=false) Optional<String> optPage
                                                      , @PathVariable(value="display", required=false) Optional<String> optDisplay) {
    // optPage와 optDisplay에 값이 존재하지 않으면 각각 기본값으로 1과 20을 사용
    int page = Integer.parseInt(optPage.orElse("1"));         // 기본 페이지 번호
    int display = Integer.parseInt(optDisplay.orElse("20"));  // 기본 한 페이지당 표시할 display 개수
    return memberService.getMembers(page, display);
  }
  
  @GetMapping(value = "/members/{memberNo}", produces = "application/json")
  // memberNo로 member 상세보기
  public ResponseEntity<Map<String, Object>> getMemberByNo(@PathVariable(value = "memberNo", required = false) Optional<String> opt){
    
    // 매개변수로 받은 회원 번호를 가져옵니다. 매개변수가 존재하지 않을 경우 기본값으로 0을 사용
    int memberNo = Integer.parseInt(opt.orElse("0"));
    
    return memberService.getMemberByNo(memberNo);
  }

}
