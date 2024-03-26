package com.gdu.prj09.dao;

import java.util.List;
import java.util.Map;

import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;

public interface MemberDao {

  int insertMember(MemberDto member);                            // 멤버 등록
  int insertAddress(AddressDto address);                         // 주소 등록
  int updateMember(MemberDto member);                            // 멤버 내용 수정 
  int deleteMember(int memberNo);                                // 멤버 번호로 멤버 삭제
  int deleteMembers(List<String> memberNoList);                  // 멤버 여러명 지우기
  int getTotalMemberCount();                                     // 등록된 멤버 총합
  List<AddressDto> getMemberList(Map<String, Object> map);       // 주소 포함해서 멤버 목록가져오기
  MemberDto getMemberByNo(int memberNo);                         // 멤버번호로 멤버 상세 조회하기 
  int getTotalAddressCountByNo(int memberNo);                    // 멤버번호로 
  List<AddressDto> getAddressListByNo(Map<String, Object> map);  // 멤버번호로 주소목록 가져오기 : begin, end, memberNo 필요
  
}
