package com.gdu.prj08.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.gdu.prj08.dto.FileDto;
import com.gdu.prj08.dto.HistoryDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileDaoImpl implements FileDao {
  
  private final SqlSessionTemplate sqlSessionTemplate;
  
  public final static String NS = "com.gdu.prj08.mybatis.mapper.file_t.";    // namespace 변수처리

  
  @Override
  public int insertHistory(HistoryDto history) {
    return sqlSessionTemplate.insert(NS + "insertHistory", history) ;
  }
  
  @Override
  public int insertFile(FileDto file) {
    return sqlSessionTemplate.insert(NS + "insertFile", file);
  }


}
