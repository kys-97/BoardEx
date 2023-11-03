package com.example.boardex.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.modelmbean.ModelMBean;

//의존성 주입을 설정하는 데 사용
//Configuration
//Spring 어플리케이션 컨텍스트(ApplicationContext)를 초기화할 때 사용되며, 이 클래스가 Spring 구성 클래스임을 나타냄
//Spring Framework에 의해 자동으로 스캔되어 빈(bean)을 정의하고 관리하는 클래스
@Configuration //bean 객체 주입
public class MapperConfig {

    // 해당 메서드가 Spring 컨테이너에 빈 객체를 등록하도록 지정
    //modelMapper라는 메서드가 ModelMapper 클래스의 객체를 생성하고 반환하는 역할
    @Bean
    //ModelMapper 클래스의 객체를 생성하여 반환
    //Java 객체 간의 매핑 및 변환을 수행하는 데 사용되는 라이브러리
    // Spring 컨테이너에 ModelMapper 객체를 등록하게 되어, 애플리케이션 내에서 필요한 곳에서 이 객체를 주입받아 사용
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
