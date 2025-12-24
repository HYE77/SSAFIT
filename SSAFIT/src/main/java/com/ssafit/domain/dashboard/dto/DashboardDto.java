package com.ssafit.domain.dashboard.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

public class DashboardDto {

    // =============================
    // 캘린더 응답 DTO
    // =============================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CalendarResponse {

        private int year;
        private int month;
        private List<CalendarDay> days;
    }

    // =============================
    // 하루 단위 DTO
    // =============================
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CalendarDay {

        private LocalDate date;
        private boolean workedOut;
    }
    
    
	// ==========================================
	// 날짜별 운동 기록 응답
	// ==========================================
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class DailyRecordResponse {
	
	    private String date;
	    private int totalMinutes;
	    private int totalCalories;
	    private List<WorkoutRecordItem> records;
	}
	
	// ==========================================
	// 개별 운동 기록 - API 응답 전용 객체. 로직을 거친 결과
	// ==========================================
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class WorkoutRecordItem {
	
	    private Long recordId;
	    private Long videoId;
	    private String videoTitle;
	    private int durationMinutes;
	    private int calorie;
	    private boolean success;
	}
	
	
	// ==========================================
	// 주간 리포트 응답 DTO
	// ==========================================
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class WeeklyReportResponse {

	    private String weekStart;   // yyyy-MM-dd
	    private String weekEnd;     // yyyy-MM-dd

	    private int workoutDays;    // 운동한 날짜 수
	    private int totalMinutes;   // 총 운동 시간(분)
	    private int totalCalories;  // 총 칼로리

	    private double avgSatisfaction;
	    private double avgDifficulty;

	    private int currentStreak;  // 현재 스트릭
	}
	
	
	// ==========================================
	// 월간 리포트 응답 DTO
	// ==========================================
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class MonthlyReportResponse {

	    private int year;
	    private int month;

	    private int workoutDays;
	    private int totalMinutes;
	    private int totalCalories;

	    private double avgSatisfaction;
	    private double avgDifficulty;

	    private int currentStreak;
	}



}
