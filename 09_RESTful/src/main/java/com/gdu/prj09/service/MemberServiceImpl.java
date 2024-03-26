package com.gdu.prj09.service;

import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gdu.prj09.dao.MemberDao;
import com.gdu.prj09.dto.AddressDto;
import com.gdu.prj09.dto.MemberDto;
import com.gdu.prj09.utils.MyPageUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  
  private final MemberDao memberDao;
  private final MyPageUtils myPageUtils;

  @Override
  public ResponseEntity<Map<String, Object>> getMembers(int page, int display) {
    
    // 등록된 멤버의 총 수
    int total = memberDao.getTotalMemberCount();
    // 페이징 처리
    myPageUtils.setPaging(total, display, page);
    // 페이징 처리를 위해 begin과 end 파라미터를 설정
    Map<String, Object> params = Map.of("begin", myPageUtils.getBegin(), "end", myPageUtils.getEnd());
    // 페이지에 맞는 멤버 목록을 가져오기 위해 MemberDao의 getMemberList 메서드를 호출
    List<AddressDto> members = memberDao.getMemberList(params);
    
    return new ResponseEntity<Map<String,Object>>(Map.of("members", members
                                                       , "total", total
                                                       , "paging", myPageUtils.getAsyncPaging())
                                                , HttpStatus.OK);
    
  }

  @Override
  public ResponseEntity<Map<String, Object>> getMemberByNo(int memberNo) {
    
    int total = memberDao.getTotalAddressCountByNo(memberNo);
    int page = 1;
    int display = 20;
    
    myPageUtils.setPaging(total, display, page);
    
    Map<String, Object> params = Map.of("memberNo", memberNo
                                      , "begin", myPageUtils.getBegin()
                                      , "end", myPageUtils.getEnd());
    
    List<AddressDto> addressList = memberDao.getAddressListByNo(params);
    MemberDto member = memberDao.getMemberByNo(memberNo);
    
    return new ResponseEntity<Map<String,Object>>(Map.of("addressList", addressList
                                                       , "member", member)
                                                , HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Map<String, Object>> registerMember(Map<String, Object> map, HttpServletResponse response) {
    
    ResponseEntity<Map<String, Object>> result = null;
    
    try {
      // 멤버 등록
      MemberDto member = MemberDto.builder()
                                  .email((String)map.get("email"))
                                  .name((String)map.get("name"))
                                  .gender((String)map.get("gender"))
                                  .build();
      int insertCount = memberDao.insertMember(member);  // 성공하면 1
      
      // 주소 등록
      AddressDto address = AddressDto.builder()
                                     .zonecode((String)map.get("zonecode"))
                                     .address((String)map.get("address"))
                                     .detailAddress((String)map.get("detailAddress"))
                                     .extraAddress((String)map.get("extraAddress"))
                                     .member(member)
                                     .build();                            
      insertCount += memberDao.insertAddress(address);  // 성공하면 1
      
      result = new ResponseEntity<Map<String,Object>>(
                   Map.of("insertCount", insertCount),   // "insertCount":2  ==> done
                   HttpStatus.OK);
          // DuplicateKeyException : 유니크 제약조건을 위반해서 생기는 예외
    } catch (DuplicateKeyException e) {  // catch(Exception e) { 예외 이름 확인 : e.getClass().getName() }
      
      try {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("이미 가입된 이메일입니다.");  // jqXHR 객체의 responseText 속성으로 확인 가능
        out.flush();
        out.close();
      } catch (Exception e2) {
        e.printStackTrace();
      }
    }    
    return result;
  }

  @Override
  public ResponseEntity<Map<String, Object>> modifyMember(MemberDto member) {
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMember(int memberNo) {
    return null;
  }

  @Override
  public ResponseEntity<Map<String, Object>> removeMembers(String memberNoList) {
    return null;
  }

}
