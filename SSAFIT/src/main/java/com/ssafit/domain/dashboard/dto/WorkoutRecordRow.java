package com.ssafit.domain.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ==========================================
// 개별 운동 기록 - DAO, Mapper 전용 객체. 가공 전 상태
// ==========================================
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutRecordRow {

    private Long recordId;
    private Long videoId;
    private String videoTitle;
    private Integer durationSeconds;
    private Integer calorieEstimate;
    private Boolean success;
}
