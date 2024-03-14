package com.gdu.prj02.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.prj02.dto.BlogDto;

@Controller
public class MyController4 {
    
  @RequestMapping("/blog/list.do") //method=RequestMethod.GET 디폴트라서 생략가능, value만 남아있을 경우 경로만 남기면 된다.
  public String list(Model model) {
    
    //DB 에서 select 한 결과
    List<BlogDto> blogList = Arrays.asList(
          new BlogDto(1, "제목1"),
          new BlogDto(2, "제목2"),
          new BlogDto(3, "제목3")
        );
    
    // Model에 저장한 값은 forward 할 때 전달된다.
    model.addAttribute("blogList", blogList);
        
    // 기본 이동 방식은 forward 방식
    return "blog/list";
    // 뷰리졸버 prefix : /WEB_INF/views/
    // 뷰리졸버 suffix : .jsp
    // 실제 리턴 : /WEB_INF/views/blog/list.jsp
  }
  
  
  @RequestMapping("/blog/detail.do")
  public String detail(@RequestParam(value="blogNo", required = false, defaultValue = "0") int blogNo, Model model) {
    
    // DB에서 가져온 데이터
    BlogDto blog = BlogDto.builder()
                          .blogNo(blogNo)
                          .title("제목" + blogNo)
                          .build();
    
    // JSP 전달할 데이터
    model.addAttribute("blog", blog);
    
    // blog/detail.jsp 로 forward 이동
    return "blog/detail";  
  }
  
  
  //@RequestMapping(value="/blog/add.do", method=RequestMethod.POST)
  public String add(BlogDto blog) {   // 커맨드 객체의 Model에 저장된다. (camelCase로 변경해서 저장한다. BlogDto -> blogDto)
    
    // blog/addResult.jsp 로 forward 
    return "blog/addResult";
  }
  
  
  @RequestMapping(value="/blog/add.do", method=RequestMethod.POST)
  public String add2(@ModelAttribute("blog")BlogDto blog) {  //@ModelAttribute : 커맨드 객체가 모델에 저장되는 이름을 지정할 때 사용한다.(이름 바꾸기)
    
    return "blog/addResult";
  }


}
