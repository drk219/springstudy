package com.gdu.prj09.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddressDto {
  
  private int addressNo;
  private String zonecode;
  private String address;
  private String detailAddress;
  private String extraAddress;
  private MemberDto member;  // MemberDto를 여기에 받아오는

}
