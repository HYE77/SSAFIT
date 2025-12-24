package com.ssafit.domain.dashboard.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.dashboard.dao.DashboardDao;
import com.ssafit.domain.dashboard.dto.DashboardDto;
import com.ssafit.domain.dashboard.dto.WeeklyAggregateRow;
import com.ssafit.domain.dashboard.dto.WorkoutRecordRow;
import com.ssafit.domain.user.dao.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private final DashboardDao dashboardDao;
    private final UserDao userDao;

    // =============================
    // 캘린더
    // =============================
    @Override
    public DashboardDto.CalendarResponse getCalendar(Long userId, int year, int month) {

        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        Set<LocalDate> workoutDates =
                new HashSet<>(dashboardDao.selectWorkoutDatesInMonth(userId, start, end));

        List<DashboardDto.CalendarDay> days =
                start.datesUntil(end.plusDays(1))
                        .map(d -> DashboardDto.CalendarDay.builder()
                                .date(d)
                                .workedOut(workoutDates.contains(d))
                                .build())
                        .toList();

        return DashboardDto.CalendarResponse.builder()
                .year(year)
                .month(month)
                .days(days)
                .build();
    }

    // =============================
    // 하루 상세
    // =============================
    @Override
    public DashboardDto.DailyRecordResponse getDailyRecord(Long userId, LocalDate date) {

        List<WorkoutRecordRow> rows =
                dashboardDao.selectRecordsByDate(userId, date);

        List<DashboardDto.WorkoutRecordItem> items =
                rows.stream()
                        .map(r -> DashboardDto.WorkoutRecordItem.builder()
                                .recordId(r.getRecordId())
                                .videoId(r.getVideoId())
                                .videoTitle(r.getVideoTitle())
                                .durationMinutes(r.getDurationSeconds() / 60)
                                .calorie(r.getCalorieEstimate())
                                .success(Boolean.TRUE.equals(r.getSuccess()))
                                .build())
                        .toList();

        int totalMinutes = items.stream().mapToInt(DashboardDto.WorkoutRecordItem::getDurationMinutes).sum();
        int totalCalories = items.stream().mapToInt(DashboardDto.WorkoutRecordItem::getCalorie).sum();

        return DashboardDto.DailyRecordResponse.builder()
                .date(date.toString())
                .totalMinutes(totalMinutes)
                .totalCalories(totalCalories)
                .records(items)
                .build();
    }

    // =============================
    // 주간 리포트
    // =============================
    @Override
    public DashboardDto.WeeklyReportResponse getWeeklyReport(
            Long userId, LocalDate start, LocalDate end
    ) {
        WeeklyAggregateRow row =
                dashboardDao.selectWeeklyAggregate(userId, start, end);

        int currentStreak = userDao.selectCurrentStreak(userId);

        if (row == null) {
            return emptyWeekly(start, end, currentStreak);
        }

        return DashboardDto.WeeklyReportResponse.builder()
                .weekStart(start.toString())
                .weekEnd(end.toString())
                .workoutDays(row.getWorkoutDays())
                .totalMinutes(row.getTotalMinutes())
                .totalCalories(row.getTotalCalories())
                .avgSatisfaction(
                        row.getAvgSatisfaction() != null ? row.getAvgSatisfaction() : 0.0
                )
                .avgDifficulty(
                        row.getAvgDifficulty() != null ? row.getAvgDifficulty() : 0.0
                )
                .currentStreak(currentStreak)
                .build();
    }

    // =============================
    // 월간 리포트
    // =============================
    @Override
    public DashboardDto.MonthlyReportResponse getMonthlyReport(
            Long userId, int year, int month
    ) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        WeeklyAggregateRow row =
                dashboardDao.selectWeeklyAggregate(userId, start, end);

        int currentStreak = userDao.selectCurrentStreak(userId);

        if (row == null) {
            return DashboardDto.MonthlyReportResponse.builder()
                    .year(year)
                    .month(month)
                    .workoutDays(0)
                    .totalMinutes(0)
                    .totalCalories(0)
                    .avgSatisfaction(0.0)
                    .avgDifficulty(0.0)
                    .currentStreak(currentStreak)
                    .build();
        }

        return DashboardDto.MonthlyReportResponse.builder()
                .year(year)
                .month(month)
                .workoutDays(row.getWorkoutDays())
                .totalMinutes(row.getTotalMinutes())
                .totalCalories(row.getTotalCalories())
                .avgSatisfaction(
                        row.getAvgSatisfaction() != null ? row.getAvgSatisfaction() : 0.0
                )
                .avgDifficulty(
                        row.getAvgDifficulty() != null ? row.getAvgDifficulty() : 0.0
                )
                .currentStreak(currentStreak)
                .build();
    }

    private DashboardDto.WeeklyReportResponse emptyWeekly(
            LocalDate start, LocalDate end, int streak
    ) {
        return DashboardDto.WeeklyReportResponse.builder()
                .weekStart(start.toString())
                .weekEnd(end.toString())
                .workoutDays(0)
                .totalMinutes(0)
                .totalCalories(0)
                .avgSatisfaction(0.0)
                .avgDifficulty(0.0)
                .currentStreak(streak)
                .build();
    }
}
