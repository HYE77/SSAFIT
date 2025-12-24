package com.ssafit.domain.board.dao;

import java.util.List;

import com.ssafit.domain.board.entity.Comment;

public interface CommentDao {

    int insertComment(Comment comment);

    Comment selectById(Long id);

    List<Comment> selectByPostId(Long postId);

    int updateComment(Comment comment);

    int softDeleteComment(Long id);
}
