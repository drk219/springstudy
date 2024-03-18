package com.gdu.mybatis_ex.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActionForward {

  private String view;
  private boolean isRedirect;
  
}
