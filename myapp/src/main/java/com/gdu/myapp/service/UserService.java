package com.gdu.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface UserService {
  
  void signin(HttpServletRequest request, HttpServletResponse response);  // 접속
  void signout(HttpServletRequest request, HttpServletResponse response); // 해제
 
  void signup(HttpServletRequest request, HttpServletResponse response);  // 가입
  void leave(HttpServletRequest request, HttpServletResponse response);   // 탈퇴

  ResponseEntity<Map<String, Object>> checkEmail(Map<String, Object> params);
  
}
