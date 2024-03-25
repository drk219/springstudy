package com.gdu.prj07.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.prj07.service.ContactService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContactScheduler {
  
  private final ContactService contactService;
  
  @Scheduled(cron = "0 41 11 * * *")
  public void execute() {
    log.info("{}개", contactService.getContactList().size());
  }
  
  /*
   * < Cron 표현식 > : 스케줄러의 정규 표현식으로 원하는 시간에 명령(프로그램)을 실행
   * 
   * 1. 형식
   *  초 분 시 일 월 요일
   *  
   * 2. 상세형식
   *  1) 초   : 0 ~ 59
   *  2) 분   : 0 ~ 59
   *  3) 시   : 0 ~ 23
   *  4) 일   : 1 ~ 31
   *  5) 월   : 0 ~ 11 또는 JAN ~ DEC
   *  6) 요일 : 0 ~ 6,7 또는 MON ~ SUN
   *  
   * 3. 키워드
   *  1) ? : 설정 없음 (요일)
   *  2) * : 모든 (초 분 시 일 월)
   *  3) - : 범위 (ex. 0 ~ 30)
   *  4) / : 반복주기 (ex. 0/10)
   *  
   * 4. 예시
   *  1) 0 0 * * * *   : 매일 모든 시간마다
   *  2) 0 0 3 * * *   : 매일 3시마다
   *  3) 0 0 3-5 * * * : 매일 3시 4시 5시마다
   *  4) 0 0 3,4 * * * : 매일 3시 5시마다
   *  5) 0 0/30 3-5 * * * : 매일 3시 3시30분 4시 4시30분 5시 5시30분마다 (30분 주기)
   *  6) 0 1/30 3-5 * * * : 매일 3시1분 3시31분 4시1분 4시31분 5시1분 5시31분마다 (1분부터 30분마다)
   */
  
}
