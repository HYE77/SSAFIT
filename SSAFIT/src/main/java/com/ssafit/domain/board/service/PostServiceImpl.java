package com.ssafit.domain.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.board.dao.CommentDao;
import com.ssafit.domain.board.dao.PostDao;
import com.ssafit.domain.board.dto.CommentDto;
import com.ssafit.domain.board.dto.PostDetailDto;
import com.ssafit.domain.board.dto.PostDto;
import com.ssafit.domain.board.entity.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostDao postDao;
    private final CommentDao commentDao;

    @Override
    public PostDto.CreateResponse createPost(Long userId, PostDto.CreateRequest request) {

        Post post = Post.builder()
                .userId(userId)
                .category(request.getCategory())
                .groupId(request.getGroupId())
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrls(request.getImageUrls())
                .viewCnt(0L)
                .likeCnt(0L)
                .commentCnt(0)
                .visibility(true)
                .deleted(false)
                .build();

        postDao.insertPost(post);

        return PostDto.CreateResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Override
    public PostDto.ReadResponse readPost(Long postId) {

        postDao.increaseViewCnt(postId);

        Post post = postDao.selectById(postId);
        if (post == null || post.getDeleted()) return null;

        return PostDto.ReadResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrls(post.getImageUrls())
                .viewCnt(post.getViewCnt())
                .likeCnt(post.getLikeCnt())
                .commentCnt(post.getCommentCnt())
                .createdAt(post.getCreatedAt())
                .build();
    }

    @Override
    public List<PostDto.ReadResponse> readAllPosts() {

        return postDao.selectAll().stream()
                .filter(p -> !p.getDeleted())
                .map(p -> PostDto.ReadResponse.builder()
                        .id(p.getId())
                        .title(p.getTitle())
                        .content(p.getContent())
                        .viewCnt(p.getViewCnt())
                        .likeCnt(p.getLikeCnt())
                        .commentCnt(p.getCommentCnt())
                        .createdAt(p.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void updatePost(Long userId, Long postId, PostDto.UpdateRequest request) {

        Post post = postDao.selectById(postId);
        if (post == null || !post.getUserId().equals(userId)) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setImageUrls(request.getImageUrls());
        post.setVisibility(request.getVisibility());

        postDao.updatePost(post);
    }

    @Override
    public PostDto.DeleteResponse deletePost(Long userId, Long postId) {

        Post post = postDao.selectById(postId);
        if (post == null || !post.getUserId().equals(userId)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }

        postDao.softDeletePost(postId);

        return PostDto.DeleteResponse.builder()
                .id(postId)
                .deleted(true)
                .build();
    }

    @Override
    public PostDetailDto.ReadResponse readPostWithComments(Long postId) {

        // 1. 조회수 증가
        postDao.increaseViewCnt(postId);

        // 2. 게시글 조회
        Post post = postDao.selectById(postId);
        if (post == null || post.getDeleted()) {
            return null;
        }

        PostDto.ReadResponse postResponse = PostDto.ReadResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrls(post.getImageUrls())
                .viewCnt(post.getViewCnt())
                .likeCnt(post.getLikeCnt())
                .commentCnt(post.getCommentCnt())
                .createdAt(post.getCreatedAt())
                .build();

        // 3. 댓글 조회
        List<CommentDto.ReadResponse> comments =
                commentDao.selectByPostId(postId).stream()
                        .filter(c -> !c.getDeleted())
                        .map(c -> CommentDto.ReadResponse.builder()
                                .id(c.getId())
                                .userId(c.getUserId())
                                .content(c.getContent())
                                .likeCnt(c.getLikeCnt())
                                .createdAt(c.getCreatedAt())
                                .build())
                        .toList();

        // 4. 합쳐서 반환
        return PostDetailDto.ReadResponse.builder()
                .post(postResponse)
                .comments(comments)
                .build();
    }

}
