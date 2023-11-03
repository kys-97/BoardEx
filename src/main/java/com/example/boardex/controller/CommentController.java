package com.example.boardex.controller;

import com.example.boardex.data.dto.CommentRequest;
import com.example.boardex.data.dto.CommentResponse;
import com.example.boardex.data.dto.PostRequest;
import com.example.boardex.data.dto.PostResponse;
import com.example.boardex.service.CommentService;
import com.example.boardex.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @Valid CommentRequest commentRequest,
                                BindingResult bindingResult) {
        PostResponse postResponse = this.postService.getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postResponse);
            return "postDetail";
        }
        CommentResponse commentResponse = this.commentService.create(postResponse, commentRequest.getContent());
        // 댓글 저장
//        this.commentService.create(post, commentRequest.getContent());
        return String.format("redirect:/post/detail/%s#comment_%s", commentResponse.getPost().getId(), commentResponse.getId());
    }

    @GetMapping("update/{id}")
    public String commentUpdate(CommentRequest commentRequest, @PathVariable("id") Integer id) {
        CommentResponse commentResponse = this.commentService.getComment(id);
        commentRequest.setContent(commentResponse.getContent());
        return "postDetail";
    }

    @PutMapping("update/{id}")
    public String commentUpdate(@Valid CommentRequest commentRequest, BindingResult bindingResult, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "postUpdate";
        }
        CommentResponse commentResponse = this.commentService.getComment(id);
        this.commentService.update(commentResponse, commentRequest.getContent());
        return String.format("redirect:/post/detail/%s", id);   // 현재 요청을 /post/detail/{id}로 리디렉션
    }

    @GetMapping("/delete/{id}")
    public String commentDelete(@PathVariable Integer id) {
        CommentResponse commentResponse = this.commentService.getComment(id);
        this.commentService.delete(commentResponse);
        return String.format("redirect:/post/detail/%s", id);
    }

}