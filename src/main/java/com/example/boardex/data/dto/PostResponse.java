package com.example.boardex.data.dto;

import com.example.boardex.data.common.BaseTimeEntity;
import com.example.boardex.data.entity.Comment;
import com.example.boardex.data.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Component
public class PostResponse extends BaseTimeEntity {
    private Integer id;
    private String subject;
    private String content;
    private List<Comment> commentList;
    private Member author;
    private int readCnt;
    private Set<MemberResponse> voter;
}
