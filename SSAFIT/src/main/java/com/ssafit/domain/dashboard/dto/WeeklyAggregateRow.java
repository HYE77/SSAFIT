package com.ssafit.domain.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ==========================================
// 주간 리포트 응답 DTO - DAO 전용 Row DTO
// ==========================================
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class WeeklyAggregateRow {

    private Integer workoutDays;
	private Integer totalMinutes;
	private Integer totalCalories;
	private Double avgSatisfaction;
	private Double avgDifficulty;
}