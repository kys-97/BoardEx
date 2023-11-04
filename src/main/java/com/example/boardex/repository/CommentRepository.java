package com.example.boardex.repository;

import com.example.boardex.data.entity.Comment;
import com.example.boardex.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findByPostId(Integer postId, PageRequest pageRequest);
}
