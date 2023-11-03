package com.example.boardex.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//RestControllerAdvice
//@ExceptionHandler, @ModelAttribute, @InitBinder 가 적용된 메서드들에 AOP를 적용해 Controller 단에 적용하기 위해 고안된 어노테이션
//클래스에 선언하면 됨
//모든 컨트롤러에 대해 전역적으로 발생할 수 있는 예외 처리
//ControllerAdvice + ResponseBody
//controllerAdvice와 동일한 역할 수행하고 추가적으로 ResponseBody를 통해 객체 리턴
//단순히 예외만 처리하고 싶다면 @ControllerAdvice
//응답으로 객체를 리턴해야 한다면 @RestControllerAdvice를 적용
@RestControllerAdvice //전역 예외 처리
// 이 클래스가 예외를 처리하고 JSON 응답을 생성할 것임을 지정
public class GlobalExceptionHandler {
    // 어플리케이션에서 발생하는 예외를 일반적인 JSON 형식의 응답으로 처리하는 역할
    // 모든 예외에 대해 동일한 형태의 응답을 생성

    //@ExceptionHandler(value = Exception.class)
    //해당 메서드가 처리할 예외 타입 지정
    //Exception 클래스의 예외를 처리하는 메서드로 설정됨
    //따라서 이 메서드는 모든 예뢰를 처리함을 알 수 있음
    @ExceptionHandler(value = Exception.class)
    //예외를 처리하고 JSON 형식의 응답을 생성하는 역할, Exception 객체를 매개변수
    public Map<String, String> handleException(Exception e) {
        Map<String, String> map = new HashMap<>();
        //예외 객체에서 예외 메시지를 추출하여 "errorMessage" 키와 함께 Map 객체에 담아 반환
        map.put("errorMessage", e.getMessage());
        //반환된 Map은 JSON 형식으로 클라이언트에게 응답으로 제공
        return map;
    }
}
