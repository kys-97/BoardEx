package com.example.boardex.data.dto;

import com.example.boardex.data.common.BaseTimeEntity;

public class MemberResponse extends BaseTimeEntity {
    private Integer id;             // 회원 번호 (PK)
    private String nickname;      //
    private String password;     // 비밀번호
    private String name;         // 이름
}

