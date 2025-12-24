package com.ssafit.domain.board.service;

import java.util.List;

import com.ssafit.domain.board.dto.CommentDto;

public interface CommentService {

    CommentDto.CreateResponse createComment(
            Long userId, Long postId, CommentDto.CreateRequest request
    );

    List<CommentDto.ReadResponse> readCommentsByPost(Long postId);

    void updateComment(
            Long userId, Long commentId, CommentDto.UpdateRequest request
    );

    CommentDto.DeleteResponse deleteComment(Long userId, Long commentId);
}
