package com.ssafit.domain.workout.service;

import java.util.List;

import com.ssafit.domain.workout.dto.WorkoutRecordDto;

public interface WorkoutRecordService {

    // ============================================================
    // 1) 운동 시작
    // ============================================================
    WorkoutRecordDto.StartResponse startWorkout(Long userId, WorkoutRecordDto.StartRequest request);

    // ============================================================
    // 2) 운동 종료
    // ============================================================
    WorkoutRecordDto.EndResponse endWorkout(
            Long userId,
            Long recordId,
            WorkoutRecordDto.EndRequest request
    );

    // ============================================================
    // 3) 내 운동 기록 목록 조회
    // ============================================================
    List<WorkoutRecordDto.ReadResponse> getMyRecords(Long userId);

    // ============================================================
    // 4) 단일 운동 기록 조회
    // ============================================================
    WorkoutRecordDto.ReadResponse getRecord(Long userId, Long recordId);
}
