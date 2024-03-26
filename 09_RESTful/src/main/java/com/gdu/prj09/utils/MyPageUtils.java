package com.gdu.prj09.utils;

import lombok.Data;

@Data
public class MyPageUtils {   // 페이징 처리 담당

  private int total;
  private int display;
  private int page;
  private int begin;
  private int end;
  // begin 과 end 가 map의 재료로 쓰인다. => list 에서 데려다 씀
  
  private int pagePerBlock = 10;
  private int totalPage;
  private int beginPage;
  private int endPage;
  
  public void setPaging(int total, int display, int page) {
    
    this.total = total;
    this.display = display;
    this.page = page;
    
    /*
     * 1 : 1 - 20
     * 2 : 21 - 40
     * 3 : 41 - 60
     */
    begin = (page - 1) * display + 1;
    end = begin + display - 1;
    
    /*
     * total  display  totalPage
     * 1000     20      1000/20 = 50.0
     * 1001     20      1000/20.0 = 50.xx = 51 (ceil 처리)
     */
    totalPage = (int) Math.ceil((double)total / display); 
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;  // 몫을 구해서 페이지 번호 구하기
    endPage = Math.min(totalPage, beginPage + pagePerBlock - 1); // 최소값으로 마지막 페이지 구하기
    
  }
  
  // 페이지 태그 만들기
  public String getAsyncPaging() {
    
    StringBuilder builder = new StringBuilder();
    
    // <
    if(beginPage == 1) {
      builder.append("<a>&lt;</a>");
    } else {
      builder.append("<a href=\"javascript:fnPaging(" + (beginPage - 1) +")\">&lt;</a>");      
    }
    
    // 1 2 3 4 5 6 7 8 9 10
    for(int p = beginPage; p <= endPage; p++) {
      if (p == page) {
        builder.append("<a>" + p + "</a>");
      } else {
        builder.append("<a href=\"javascript:fnPaging(" + p + ")\">" + p + "</a>");        
      }
    }
    
    // >
    if(endPage == totalPage) {
      builder.append("<a>&gt;</a>");
    } else {
      builder.append("<a href=\"javascript:fnPaging(" + (endPage + 1) + ")\">&gt;</a>");      
    }
    
    return builder.toString();
  }
}
