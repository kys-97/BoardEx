package com.example.boardex.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
