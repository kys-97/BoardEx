package com.example.boardex.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ResponseStatus
//응답 상태를 지정할 때 사용하는 어노테이션
//해당 예외가 발생했을 때 HTTP 응답 상태 코드와 이유(reason)를 설정
//예외가 발생하면 HTTP 상태 코드를 404(Not Found)로 설정하고 "Entity Not Found"라는 이유를 제공
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity Not Found")
//RuntimeException 클래스를 상속받는 예외 클래스
//RuntimeException은 일반 예외(checked exception)가 아닌 런타임 예외(unchecked exception)이며, 보통 프로그램의 논리적 오류나 예기치 않은 상황을 나타내는데 사용
public class DataNotFoundException extends RuntimeException {
    //serialVersionUID? ->  Java 직렬화(serialization)에서 사용 / 직렬화는 객체를 이진 형식으로 저장하거나 전송하는 데 사용
    //serialVersionUID는 직렬화된 객체가 변경되지 않도록 하기 위한 고유 식별자로 사용
    // 모든 Class는 UID를 가짐 -> Class의 내용이 변경되면 UID값 역시 함께 변경
    // 직렬화하여 통신 -> UID값으로 정상인지 확인 -> 그 값이 바뀌게 되면 다른 Class로 인식
    //이를 방지하기 위해 고유값으로 미리 명시해주기 위해 serialVersionUID 사용
    //직렬화: 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로 데이터 변환하는 기술 혹은 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)을 아울러서 칭함
    private static final long serialVersionUID = 1873527683909274536L;
    //생성자는 예외 객체를 생성할 때 예외 메시지를 전달
    //예외 객체를 생성하면서 메시지를 전달하여 어떤 상황에서 예외가 발생했는지 설명
    public DataNotFoundException(String message) {
        //당 예외가 발생할 때는 HTTP 상태 코드 404(Not Found)와 "Entity Not Found"라는 이유를 함께 반환
        //클라이언트에게 적절한 응답을 제공
        super(message);
    }
}
