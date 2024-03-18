package com.gdu.mybatis_ex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDto {
  
  private int rn;
  private int studentNo;
  private String name;
  private int korean;
  private int english;
  private int math;

}
