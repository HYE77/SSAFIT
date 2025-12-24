package com.ssafit.domain.board.dto;

import java.time.LocalDateTime;
import lombok.*;

public class PostDto {

    // ============================================================
    // 게시글 작성 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {
        private Long category;
        private String title;
        private String content;
        private String imageUrls; // JSON 문자열
        private Long groupId; // null이면 전체 게시판
    }

    // ============================================================
    // 게시글 작성 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResponse {
        private Long id;
        private String title;
        private LocalDateTime createdAt;
    }

    // ============================================================
    // 게시글 조회 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {
        private Long id;
        private String title;
        private String content;
        private Long groupId;
        private String imageUrls;
        private Long viewCnt;
        private Long likeCnt;
        private Integer commentCnt;
        private LocalDateTime createdAt;
    }

    // ============================================================
    // 게시글 수정 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateRequest {
        private String title;
        private String content;
        private String imageUrls;
        private Boolean visibility;
    }

    // ============================================================
    // 게시글 삭제 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeleteResponse {
        private Long id;
        private Boolean deleted;
    }
}
