package com.example.boardex.service;

import com.example.boardex.config.DataNotFoundException;
import com.example.boardex.data.dto.CommentResponse;
import com.example.boardex.data.dto.PostResponse;
import com.example.boardex.data.entity.Comment;
import com.example.boardex.data.entity.Post;
import com.example.boardex.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private Comment of(CommentResponse commentResponse) {
        return modelMapper.map(commentResponse, Comment.class);
    }
    private CommentResponse of(Comment comment) {
        return modelMapper.map(comment, CommentResponse.class);
    }

    //list
    public Page<CommentResponse> getList(Integer postId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<Comment> commentPage = commentRepository.findByPostId(postId, pageRequest);

        if (commentPage.isEmpty()) {
            throw new DataNotFoundException("댓글이 없습니다.");
        }

        return commentPage.map(this::of);
    }



    public CommentResponse create(PostResponse postResponse, String content) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(content);
        commentResponse.setCreatedDate(LocalDateTime.now());
        commentResponse.setPost(postResponse);
        Comment comment = of(commentResponse);
        comment = this.commentRepository.save(comment);
        commentResponse.setId(comment.getId());
        System.out.println("comment service: create");
        return commentResponse;
    }

    public CommentResponse getComment(Integer id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        System.out.println("comment service: getComment");
        if (comment.isPresent()) {
            return  of(comment.get());
        }else {
            throw new DataNotFoundException("해당 게시글이 없습니다");
        }

    }

    //update


    public void delete(CommentResponse commentResponse) {
        this.commentRepository.deleteById(commentResponse.getId());
    }

}
