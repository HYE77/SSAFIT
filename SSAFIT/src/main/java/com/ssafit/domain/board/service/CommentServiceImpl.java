package com.ssafit.domain.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.board.dao.CommentDao;
import com.ssafit.domain.board.dto.CommentDto;
import com.ssafit.domain.board.entity.Comment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public CommentDto.CreateResponse createComment(
            Long userId, Long postId, CommentDto.CreateRequest request) {

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(request.getContent())
                .likeCnt(0L)
                .deleted(false)
                .build();

        commentDao.insertComment(comment);

        return CommentDto.CreateResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    @Override
    public List<CommentDto.ReadResponse> readCommentsByPost(Long postId) {

        return commentDao.selectByPostId(postId).stream()
                .filter(c -> !c.getDeleted())
                .map(c -> CommentDto.ReadResponse.builder()
                        .id(c.getId())
                        .userId(c.getUserId())
                        .content(c.getContent())
                        .likeCnt(c.getLikeCnt())
                        .createdAt(c.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void updateComment(
            Long userId, Long commentId, CommentDto.UpdateRequest request) {

        Comment comment = commentDao.selectById(commentId);

        if (comment == null || !comment.getUserId().equals(userId)) {
            throw new RuntimeException("댓글 수정 권한이 없습니다.");
        }

        comment.setContent(request.getContent());
        commentDao.updateComment(comment);
    }

    @Override
    public CommentDto.DeleteResponse deleteComment(Long userId, Long commentId) {

        Comment comment = commentDao.selectById(commentId);

        if (comment == null || !comment.getUserId().equals(userId)) {
            throw new RuntimeException("댓글 삭제 권한이 없습니다.");
        }

        commentDao.softDeleteComment(commentId);

        return CommentDto.DeleteResponse.builder()
                .id(commentId)
                .deleted(true)
                .build();
    }
}
