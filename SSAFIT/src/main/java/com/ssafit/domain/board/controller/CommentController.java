package com.ssafit.domain.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.board.dto.CommentDto;
import com.ssafit.domain.board.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
@Tag(name = "Comment API", description = "게시글 댓글 관련 REST API")
public class CommentController {

    private final CommentService commentService;

    // ============================================================
    // C: 댓글 작성 (JWT 필요)
    // ============================================================
    @Operation(
        summary = "댓글 작성",
        description = "JWT 인증된 사용자가 게시글에 댓글을 작성합니다."
    )
    @PostMapping
    public ResponseEntity<CommentDto.CreateResponse> createComment(
            @Parameter(hidden = true) Authentication authentication,
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long postId,
            @RequestBody CommentDto.CreateRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return ResponseEntity.ok(
                commentService.createComment(userId, postId, request)
        );
    }

    // ============================================================
    // R: 댓글 목록 조회 (Public)
    // ============================================================
    @Operation(
        summary = "댓글 목록 조회",
        description = "게시글에 달린 댓글 목록을 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<CommentDto.ReadResponse>> readComments(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                commentService.readCommentsByPost(postId)
        );
    }

    // ============================================================
    // U: 댓글 수정 (JWT 필요)
    // ============================================================
    @Operation(
        summary = "댓글 수정",
        description = "JWT 인증된 작성자가 본인의 댓글을 수정합니다."
    )
    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @Parameter(hidden = true) Authentication authentication,
            @Parameter(description = "댓글 ID", example = "1")
            @PathVariable Long commentId,
            @RequestBody CommentDto.UpdateRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        commentService.updateComment(userId, commentId, request);
        return ResponseEntity.ok().build();
    }

    // ============================================================
    // D: 댓글 삭제 (Soft Delete, JWT 필요)
    // ============================================================
    @Operation(
        summary = "댓글 삭제",
        description = "JWT 인증된 작성자가 본인의 댓글을 삭제합니다. (Soft Delete)"
    )
    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDto.DeleteResponse> deleteComment(
            @Parameter(hidden = true) Authentication authentication,
            @Parameter(description = "댓글 ID", example = "1")
            @PathVariable Long commentId) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return ResponseEntity.ok(
                commentService.deleteComment(userId, commentId)
        );
    }
}
