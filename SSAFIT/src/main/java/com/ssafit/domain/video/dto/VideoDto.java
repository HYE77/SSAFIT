package com.ssafit.domain.video.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class VideoDto {

    // ============================================================
    // 1. 영상 등록 요청 DTO
    // ============================================================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {

        private String title;
        private String description;

        private String videoUrl;
        private String thumbnailUrl;

        private Long durationSeconds;

        private Integer difficulty;
        private String targetPart;

        private Integer calorieEstimate;
        private String category;

        // JWT 인증 기반 admin id
        private Long createdBy;
    }


    // ============================================================
    // 2. 영상 등록 응답 DTO
    // ============================================================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateResponse {

        private Long id;
        private String title;
        private Boolean visibility;
        private LocalDate createdAt;
    }


    // ============================================================
    // 3. 영상 조회 응답 DTO
    // ============================================================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReadResponse {

        private Long id;

        private String title;
        private String description;

        private String videoUrl;
        private String thumbnailUrl;

        private Long durationSeconds;

        private Integer difficulty;
        private String targetPart;

        private Integer calorieEstimate;
        private String category;

        private Long viewCnt;
        private Long likeCnt;
        private Long reviewCnt;

        private LocalDate createdAt;
        private LocalDate updatedAt;

        private Boolean visibility;
    }


    // ============================================================
    // 4. 영상 정보 수정 요청 DTO
    // ============================================================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateRequest {

        private String title;
        private String description;

        private String thumbnailUrl;

        private Integer difficulty;
        private String targetPart;

        private Integer calorieEstimate;
        private String category;

        private Boolean visibility;
    }


    // ============================================================
    // 5. 영상 정보 수정 응답 DTO
    // ============================================================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateResponse {

        private Long id;
        private String title;
        private Boolean visibility;
        private LocalDate updatedAt;
    }


    // ============================================================
    // 6. 영상 정보 삭제 응답 DTO
    // ============================================================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DeleteResponse {

        private Long id;
        private Boolean deleted;
    }
    
    
    // ============================================================
    // 7. 영상 평균 만족도/난이도 계산 based on reviews
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReviewSummaryResponse {
        private Long videoId;
        private Double avgSatisfaction;
        private Double avgDifficulty;
        private Long reviewCnt;
    }
    
    
    // ============================================================
    // 8. 영상 세부 정보 응답 DTO (상세 내용, 리뷰 요약, 리뷰 목록)
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class VideoDetailResponse {

        // 1. 영상 기본 정보
        private Long id;
        private String title;
        private String description;
        private String videoUrl;
        private String thumbnailUrl;
        private Long durationSeconds;
        private Integer difficulty;
        private String targetPart;
        private Integer calorieEstimate;
        private String category;
        private Long viewCnt;
        private Long likeCnt;
        private Long reviewCnt;
        private Boolean visibility;

        // 2. 리뷰 요약
        private ReviewSummaryResponse reviewSummary;

        // 3. 리뷰 목록
        private List<VideoReviewDto.ReadResponse> reviews;
    }

}
