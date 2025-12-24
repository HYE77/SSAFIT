package com.ssafit.domain.dashboard.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.dashboard.dto.DashboardDto;
import com.ssafit.domain.dashboard.service.DashboardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard API", description = "대시보드(캘린더, 리포트) 관련 API")
public class DashboardController {

    private final DashboardService dashboardService;

    // ============================================================
    // 1. 운동 캘린더 조회
    // ============================================================
    @Operation(
        summary = "운동 캘린더 조회",
        description = "특정 연/월의 운동 여부를 캘린더 형태로 조회합니다."
    )
    @GetMapping("/calendar")
    public ResponseEntity<DashboardDto.CalendarResponse> getCalendar(
            @Parameter(hidden = true)
            @RequestAttribute("userId") Long userId,

            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(
                dashboardService.getCalendar(userId, year, month)
        );
    }

    // ============================================================
    // 2. 특정 날짜 운동 기록 조회
    // ============================================================
    @Operation(
        summary = "하루 운동 기록 조회",
        description = "특정 날짜의 운동 기록 상세를 조회합니다."
    )
    @GetMapping("/daily")
    public ResponseEntity<DashboardDto.DailyRecordResponse> getDailyRecord(
            @Parameter(hidden = true)
            @RequestAttribute("userId") Long userId,

            @RequestParam String date   // yyyy-MM-dd
    ) {
        LocalDate parsedDate = LocalDate.parse(date);

        return ResponseEntity.ok(
                dashboardService.getDailyRecord(userId, parsedDate)
        );
    }

    // ============================================================
    // 3. 주간 리포트 조회
    // ============================================================
    @Operation(
        summary = "주간 운동 리포트 조회",
        description = "특정 기간(주간)의 운동 통계를 조회합니다."
    )
    @GetMapping("/weekly")
    public ResponseEntity<DashboardDto.WeeklyReportResponse> getWeeklyReport(
            @Parameter(hidden = true)
            @RequestAttribute("userId") Long userId,

            @RequestParam String start, // yyyy-MM-dd
            @RequestParam String end    // yyyy-MM-dd
    ) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return ResponseEntity.ok(
                dashboardService.getWeeklyReport(userId, startDate, endDate)
        );
    }

    // ============================================================
    // 4. 월간 리포트 조회
    // ============================================================
    @Operation(
        summary = "월간 운동 리포트 조회",
        description = "특정 연/월의 운동 통계를 조회합니다."
    )
    @GetMapping("/monthly")
    public ResponseEntity<DashboardDto.MonthlyReportResponse> getMonthlyReport(
            @Parameter(hidden = true)
            @RequestAttribute("userId") Long userId,

            @RequestParam int year,
            @RequestParam int month
    ) {
        return ResponseEntity.ok(
                dashboardService.getMonthlyReport(userId, year, month)
        );
    }
}
