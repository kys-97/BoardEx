package com.example.boardex.service;

import com.example.boardex.config.DataNotFoundException;
import com.example.boardex.data.dto.PostResponse;
import com.example.boardex.data.entity.Post;
import com.example.boardex.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository pr;
    private final ModelMapper modelMapper;

    private PostResponse of (Post post) {
        return modelMapper.map(post, PostResponse.class);
    }

    private Post of (PostResponse postResponse) {
        return modelMapper.map(postResponse, Post.class);
    }

    //get All List
    public List<Post> getList() {
        return this.pr.findAll();
    }

    public PostResponse getPost(Integer id) {
        Optional<Post> post = this.pr.findById(id);
        if (post.isPresent()) {
            Post p1 = post.get();
            p1.setReadCnt(p1.getReadCnt()+1);
            this.pr.save(p1);
            return of(post.get());
        }
        else {
            throw new DataNotFoundException("해당 글이 없습니다");
        }
    }

    public Page<Post> getPage(String kw, int page) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(Sort.Order.desc("createdDate")); //createdDate 필드를 내림차순으로 정렬, desc : 역순(최근 날짜순)

        Pageable pageable = PageRequest.of(page, 10, Sort.by(orders)); //정렬 정보를 포함한 Pageable 생성

        //검색해서 보여 주는 기능
        if (kw != null && !kw.isEmpty()) {  //키워드가 null이 아니거라, 비어있지 않거나 둘 다 넣어서 확실하게 하려고
            return pr.findBySubjectContaining(kw, pageable); //키워드가 포함된 걸 보여주고
        } else {
            return this.pr.findAll(pageable); //검색어가 비어 있으면 전체를 보여줘라
        }

    }
    public void create(String subject, String content) {
        Post q = new Post();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreatedDate(LocalDateTime.now());
        this.pr.save(q);
    }

    public PostResponse update(PostResponse postResponse, String subject, String content) {
        postResponse.setSubject(subject);
        postResponse.setContent(content);
        postResponse.setUpdatedDate(LocalDateTime.now());
        Post post = of(postResponse);
        this.pr.save(post);
        return postResponse;
    }

    public void delete(PostResponse postResponse) {
        this.pr.deleteById(postResponse.getId());
    }

}
