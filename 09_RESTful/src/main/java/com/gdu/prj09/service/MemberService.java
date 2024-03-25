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
  ResponseEntity<Map<String, Object>> getMembers(int page, int display);
  ResponseEntity<MemberDto> getMemberByNo(int memberNo);
  ResponseEntity<Map<String, Object>> registerMember(Map<String, Object> map, HttpServletResponse response);
  ResponseEntity<Map<String, Object>> modifyMember(MemberDto member);
  ResponseEntity<Map<String, Object>> removeMember(int memberNo);
  ResponseEntity<Map<String, Object>> removeMembers(String memberNoList); // 여러명을 삭제할때는 int가 아니라 String으로 가져온다
  
}
