package com.gdu.prj10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
  
  @GetMapping(value={"/", "/main.do"})  // index.jsp는 main.do라고 칭하기로 설정
  public String welcome() {
    return "index";
  }
  
}
