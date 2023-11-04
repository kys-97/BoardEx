package com.example.boardex.controller;

import com.example.boardex.data.dto.CommentRequest;
import com.example.boardex.data.dto.CommentResponse;
import com.example.boardex.data.dto.PostRequest;
import com.example.boardex.data.dto.PostResponse;
import com.example.boardex.data.entity.Comment;
import com.example.boardex.service.CommentService;
import com.example.boardex.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;

    //list
    @GetMapping("/list/{postId}")
    public String list(@PathVariable Integer postId, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        Page<CommentResponse> commentPage = commentService.getList(postId, page, size);
        PostResponse postResponse = postService.getPost(postId);

        model.addAttribute("post", postResponse);
        model.addAttribute("commentPage", commentPage);

        return "postDetail"; // 여기에 댓글 목록을 보여주는 뷰 페이지 이름을 입력
    }

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Integer id, @Valid CommentRequest commentRequest,
                                BindingResult bindingResult) {
        PostResponse postResponse = this.postService.getPost(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postResponse);
            return "postDetail";
        }
        CommentResponse commentResponse = this.commentService.create(postResponse, commentRequest.getContent());
        System.out.println("comment controller: createComment");
        // 댓글 저장
//        this.commentService.create(post, commentRequest.getContent());
        return String.format("redirect:/post/detail/%s#comment_%s", commentResponse.getPost().getId(), commentResponse.getId());

    }

    @GetMapping("/delete/{id}")
    public String commentDelete(@PathVariable Integer id) {
        CommentResponse commentResponse = this.commentService.getComment(id);
        this.commentService.delete(commentResponse);
        return String.format("redirect:/post/detail/%s", id);
    }

    //update?

}