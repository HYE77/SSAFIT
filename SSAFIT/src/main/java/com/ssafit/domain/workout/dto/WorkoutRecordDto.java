package com.ssafit.domain.workout.dto;

import java.time.LocalDateTime;

import lombok.*;

public class WorkoutRecordDto {

    // ============================================================
    // 1) 운동 시작 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class StartRequest {
        private Long videoId;
    }

    // ============================================================
    // 2) 운동 시작 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class StartResponse {
        private Long recordId;
        private LocalDateTime startTime;
    }

    // ============================================================
    // 3) 운동 종료 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class EndRequest {
        private Integer userSatisfaction;     // 사용자 만족도
        private Integer personalDifficulty;   // 체감 난이도
        private String userMemo;              // 사용자 메모
        private Boolean isSuccess;             // 성공 여부
    }

    // ============================================================
    // 4) 운동 종료 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class EndResponse {
        private Long recordId;
        private LocalDateTime endTime;
        private Boolean isSuccess;
    }

    // ============================================================
    // 5) 내 운동 기록 조회 응답 (리스트/단건 공용)
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {
        private Long recordId;
        private Long videoId;

        private LocalDateTime startTime;
        private LocalDateTime endTime;

        private Integer userSatisfaction;
        private Integer personalDifficulty;
        private String userMemo;

        private Boolean isSuccess;
    }
}
