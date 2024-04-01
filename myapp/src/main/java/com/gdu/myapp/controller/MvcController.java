package com.gdu.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {
  
  @GetMapping(value={"/", "/main.page"})  // index.jsp는 main.do라고 칭하기로 설정
  public String welcome() {
    return "index";
  }
  
}
