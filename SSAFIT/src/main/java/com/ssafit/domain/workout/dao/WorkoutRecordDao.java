package com.ssafit.domain.workout.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.domain.workout.entity.WorkoutRecord;

public interface WorkoutRecordDao {

    // 운동 시작 기록 생성
    int insertRecord(WorkoutRecord record);

    // 단일 기록 조회
    WorkoutRecord selectById(@Param("id") Long id);

    // 내 기록 목록 조회 (최신순)
    List<WorkoutRecord> selectByUserId(@Param("userId") Long userId);

    // 운동 종료(기록 업데이트)
    int updateRecord(WorkoutRecord record);

    // 진행 중 운동 존재 여부 (end_time IS NULL)
    boolean existsOngoing(@Param("userId") Long userId);
    
    // 특정 날짜에 성공한 운동 존재 여부 (스트릭 계산용)
    boolean existsSuccessOnDate(
            @Param("userId") Long userId,
            @Param("date") LocalDate date
    );
    
}
