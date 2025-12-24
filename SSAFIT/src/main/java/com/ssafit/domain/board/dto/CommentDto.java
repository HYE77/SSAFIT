package com.ssafit.domain.board.dto;

import java.time.LocalDateTime;
import lombok.*;

public class CommentDto {

    // ============================================================
    // 댓글 작성 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {
        private String content;
    }

    // ============================================================
    // 댓글 작성 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResponse {
        private Long id;
        private String content;
        private LocalDateTime createdAt;
    }

    // ============================================================
    // 댓글 조회 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {
        private Long id;
        private Long userId;
        private String content;
        private Long likeCnt;
        private LocalDateTime createdAt;
    }

    // ============================================================
    // 댓글 수정 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateRequest {
        private String content;
    }

    // ============================================================
    // 댓글 삭제 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeleteResponse {
        private Long id;
        private Boolean deleted;
    }
}
