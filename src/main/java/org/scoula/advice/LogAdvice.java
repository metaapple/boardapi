package org.scoula.advice;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //관점클래스로 지정(로그인/로그아웃/예외처리같은 공통적인 기능을 모아놓은 클래스)
@Component
class LogAdvice {

}
