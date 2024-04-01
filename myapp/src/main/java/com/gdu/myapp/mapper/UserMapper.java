package com.gdu.myapp.mapper;

import java.util.Map;

import com.gdu.myapp.dto.LeaveUserDto;
import com.gdu.myapp.dto.UserDto;

public interface UserMapper {
    // -> 인터페이스랑 매퍼 네임스페이스 맞출것 

  // 매퍼 아이디랑 맞출것
  UserDto getUserByMap(Map<String, Object> params); 
  int insertAccessHistory(Map<String, Object> params);
  LeaveUserDto getLeaveUserByMap(Map<String, Object> map);
  int insertUser(UserDto user);
  
}