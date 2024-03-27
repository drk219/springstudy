package com.gdu.prj09.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.gdu.prj09.dto.MemberDto;

public interface MemberService {
  
  /*
   * HttpServletRequest
   * @RequestParam
   * MyPageUtils : setter
   */
  ResponseEntity<Map<String, Object>> getMembers(int page, int display);  // 한 페이지 display 에 출력할
  ResponseEntity<Map<String, Object>> getMemberByNo(int memberNo);        // 번호로 멤버 조회
  ResponseEntity<Map<String, Object>> registerMember(Map<String, Object> map, HttpServletResponse response);
  ResponseEntity<Map<String, Object>> modifyMember(Map<String, Object> map);
  ResponseEntity<Map<String, Object>> removeMember(int memberNo);
  ResponseEntity<Map<String, Object>> removeMembers(String memberNoList); // 여러명을 삭제할 때는 int 가 아니라 String 으로 가져온다
  
}
