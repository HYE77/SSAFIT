package com.ssafit.domain.video.dto;

import java.time.LocalDateTime;
import lombok.*;

public class VideoReviewDto {

    // ============================================================
    // 리뷰 작성 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {

        private Integer satisfaction; // 만족도 (1~5)
        private Integer difficulty;   // 체감 난이도 (1~5)
        private String content;
    }

    // ============================================================
    // 리뷰 작성 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResponse {

        private Long id;
        private LocalDateTime createdTime;
    }

    // ============================================================
    // 리뷰 조회 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {

        private Long id;
        private Long userId;

        private Integer satisfaction;
        private Integer difficulty;

        private String content;
        private LocalDateTime createdTime;
    }

    // ============================================================
    // 리뷰 수정 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateRequest {

        private Integer satisfaction;
        private Integer difficulty;
        private String content;
    }

    // ============================================================
    // 리뷰 삭제 응답 (하드 삭제)
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class DeleteResponse {

        private Long id;
        private Boolean deleted;
    }

}
