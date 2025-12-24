package com.ssafit.domain.board.service;

import java.util.List;

import com.ssafit.domain.board.dto.PostDetailDto;
import com.ssafit.domain.board.dto.PostDto;

public interface PostService {

    PostDto.CreateResponse createPost(Long userId, PostDto.CreateRequest request);

    PostDto.ReadResponse readPost(Long postId);
    
    PostDetailDto.ReadResponse readPostWithComments(Long postId);

    List<PostDto.ReadResponse> readAllPosts();

    void updatePost(Long userId, Long postId, PostDto.UpdateRequest request);

    PostDto.DeleteResponse deletePost(Long userId, Long postId);
}
