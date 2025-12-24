package com.ssafit.domain.dashboard.service;

import java.time.LocalDate;

import com.ssafit.domain.dashboard.dto.DashboardDto;

public interface DashboardService {

    DashboardDto.CalendarResponse getCalendar(
            Long userId, int year, int month
    );

    DashboardDto.DailyRecordResponse getDailyRecord(
            Long userId, LocalDate date
    );

    DashboardDto.WeeklyReportResponse getWeeklyReport(
            Long userId, LocalDate start, LocalDate end
    );

    DashboardDto.MonthlyReportResponse getMonthlyReport(
            Long userId, int year, int month
    );
}
