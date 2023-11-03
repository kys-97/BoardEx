package com.example.boardex.data.common;

public interface EntityMapper <DTO, Entity> {
    //Entity로 가져온 데이터를 response DTO에 바인딩
    //Entity class에서 필요한 데이터만 선택적으로 DTO에 담아 생성
    //Entity class를 감추며 보호할 수 있음
    Entity toEntity(DTO dto);
}

/*
* ModelMapper vs MapStruct
* 하나의 객체의 값을 다른 객체에 바인딩시킬 때 사용
* 바인딩: 프로그램에 사용된 구성 요소의 실제 값 또는 프로퍼티를 결정짓는 행위
* 보통 Entity로 가져온 데이터를 Response DTO에 바인딩 시킬 때 쓰는 라이브러리
*
* */
