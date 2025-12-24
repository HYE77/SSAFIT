package com.ssafit.domain.dashboard.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.domain.dashboard.dto.WeeklyAggregateRow;
import com.ssafit.domain.dashboard.dto.WorkoutRecordRow;

public interface DashboardDao {

    // 월 단위 운동한 날짜 조회
    List<LocalDate> selectWorkoutDatesInMonth(
            @Param("userId") Long userId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
    
    // 날짜 별 운동 기록 조회
    List<WorkoutRecordRow> selectRecordsByDate(
            @Param("userId") Long userId,
            @Param("date") LocalDate date
    );
    
    // 주간 운동 기록 조회
    WeeklyAggregateRow selectWeeklyAggregate(
            @Param("userId") Long userId,
            @Param("start") LocalDate start,
            @Param("end") LocalDate end
    );
    
}
