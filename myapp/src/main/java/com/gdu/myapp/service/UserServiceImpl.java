package com.gdu.myapp.service;

import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.myapp.dto.UserDto;
import com.gdu.myapp.mapper.UserMapper;
import com.gdu.myapp.utils.MySecurityUtils;

@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  
  public UserServiceImpl(UserMapper userMapper) {
    super();
    this.userMapper = userMapper;
  }
  
  @Override
  public void signin(HttpServletRequest request, HttpServletResponse response) {

    try {
      
      // 입력한 아이디
      String email = request.getParameter("email");
      
      // 입력한 비밀번호 + SHA-256 방식의 암호화
      String pw = MySecurityUtils.getSha256(request.getParameter("pw"));
      
      // 접속한 IP (접속 기록을 남길 때 필요한 정보)
      String ip = request.getRemoteAddr();
      
      // 디비로 보낼 정보 (email/pw : USER_T, email/ip : ACCESS_HISTORY_T)
      Map<String, Object> params = Map.of("email", email, "pw", pw, "ip", ip);
      
      // email/pw 가 일치하는 회원 정보 가져오기
      UserDto user = userMapper.getUserByMap(params);
      
      // 일치하는 회원 있음 (Sign in 성공)
      if(user != null) {
        
        // 접속 기록 ACCESS_HISTORY_T에 남기기
        userMapper.insertAccessHistory(params);
        // 회원 정보를 세션(브라우저 닫기 전까지 정보가 유지되는 공간, 기본 30분 정보 유지)에 보관하기
        request.getSession().setAttribute("user", user);
        // SIgnIn 후에 페이지 이동 
        response.sendRedirect(request.getParameter("url"));
        
        // 일치하는 회원 없음 (Sign In 실패)
      } else {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('일치하는 회원 정보가 없습니다.')");
        out.println("location.href='" + request.getContextPath() + "/main.page'");
        out.println("</script>");
        out.flush();
        out.close();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  @Override
  public ResponseEntity<Map<String, Object>> checkEmail(Map<String, Object> params) {
    
    // 사용하려는 이메일의 존재 여부 확인하기 
    boolean enableEmail = userMapper.getUserByMap(params) == null  
                       && userMapper.getLeaveUserByMap(params) == null;  // enableEmail이 없어야 사용가능 
    
    return new ResponseEntity<>(Map.of("enableEmail", enableEmail), HttpStatus.OK);
  }

  @Override
  public void signout(HttpServletRequest request, HttpServletResponse response) {
    
    

  }

  @Override
  public void signup(HttpServletRequest request, HttpServletResponse response) {
    
    

  }

  @Override
  public void leave(HttpServletRequest request, HttpServletResponse response) {
    
    

  }

}
