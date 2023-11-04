package com.example.boardex.controller;

import com.example.boardex.data.dto.CommentRequest;
import com.example.boardex.data.dto.PostRequest;
import com.example.boardex.data.dto.PostResponse;
import com.example.boardex.data.entity.Post;
import com.example.boardex.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final PostResponse postResponse;


    @GetMapping("/list")    //--> "/post/list" 와 같음
    public String list(Model model) {
        List<Post> postList = this.postService.getList();
        model.addAttribute("postList", postList);
        return "listPage";
    }


    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, CommentRequest commentRequest) {
        PostResponse post = this.postService.getPost(id);
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/create")
    public String postCreate(PostRequest postRequest) {
        return "postForm";
    }


    //  PostRequest --> @NotEmpty, @Size 등으로 설정한 검증 기능 동작
// BindingResult 매개변수는 @Valid 애너테이션으로 인해 검증이 수행된 결과를 의미하는 객체이다.
    @PostMapping("/create")
    public String postCreate(@Valid PostRequest postRequest, BindingResult bindingResult) {
        // TODO 게시글 저장
        if (bindingResult.hasErrors()) {
            return "postForm";
        }
//        this.postService.create(subject, content);
        this.postService.create(postRequest.getSubject(), postRequest.getContent());

        return "redirect:/post/listPage";
    }

    @GetMapping("update/{id}")
    public String postUpdate(PostRequest postRequest, @PathVariable("id") Integer id) {
        PostResponse postResponse = this.postService.getPost(id);
        postRequest.setSubject(postResponse.getSubject());
        postRequest.setContent(postResponse.getContent());
        return "postUpdate";
    }

    @PostMapping ("update/{id}")
    public String postUpdate(@Valid PostRequest postRequest, BindingResult bindingResult, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "postUpdate";
        }
        PostResponse postResponse = this.postService.getPost(id);
        this.postService.update(postResponse, postRequest.getSubject(), postRequest.getContent());
        return String.format("redirect:/post/detail/%s", id);   // 현재 요청을 /post/detail/{id}로 리디렉션
    }



    @GetMapping("/delete/{id}")
    public String postdelete(@PathVariable Integer id) {
        PostResponse postResponse = this.postService.getPost(id);
        this.postService.delete(postResponse);
        return "redirect:/post/listPage";
    }


}
