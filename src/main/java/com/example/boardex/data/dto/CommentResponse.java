package com.example.boardex.data.dto;

import com.example.boardex.data.common.BaseTimeEntity;
import lombok.Data;

import java.util.Set;

@Data
public class CommentResponse extends BaseTimeEntity {
    private Integer id;
    private String content;
    private PostResponse post;
    private MemberResponse author;
    private Set<MemberResponse> voter;
}
