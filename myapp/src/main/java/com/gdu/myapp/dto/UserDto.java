package com.gdu.myapp.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
  
  private int userNo , evertAgree, signupKind;
  private String email, pw, name, gender, mobile;
  private Date pwModifyDt, signupDt;

}
