package com.ssafit.domain.workout.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.workout.dto.WorkoutRecordDto;
import com.ssafit.domain.workout.service.WorkoutRecordService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/workouts")
@Tag(name = "Workout Record API", description = "운동 기록 관련 REST API")
public class WorkoutRecordController {

    private final WorkoutRecordService workoutRecordService;

    // ============================================================
    // 1) 운동 시작
    // ============================================================
    @Operation(summary = "운동 시작", description = "영상 기반 운동을 시작하고 기록을 생성합니다.")
    @PostMapping("/start")
    public ResponseEntity<WorkoutRecordDto.StartResponse> startWorkout(
            @Parameter(hidden = true) Authentication authentication,
            @RequestBody WorkoutRecordDto.StartRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());

        return ResponseEntity.ok(
                workoutRecordService.startWorkout(userId, request)
        );
    }

    // ============================================================
    // 2) 운동 종료
    // ============================================================
    @Operation(summary = "운동 종료", description = "운동을 종료하고 결과를 기록합니다.")
    @PostMapping("/{recordId}/end")
    public ResponseEntity<WorkoutRecordDto.EndResponse> endWorkout(
            @Parameter(hidden = true) Authentication authentication,
            @PathVariable Long recordId,
            @RequestBody WorkoutRecordDto.EndRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());

        return ResponseEntity.ok(
                workoutRecordService.endWorkout(userId, recordId, request)
        );
    }

    // ============================================================
    // 3) 내 운동 기록 목록 조회
    // ============================================================
    @Operation(summary = "내 운동 기록 목록 조회")
    @GetMapping("/me")
    public ResponseEntity<List<WorkoutRecordDto.ReadResponse>> getMyRecords(
            @Parameter(hidden = true) Authentication authentication) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());

        return ResponseEntity.ok(
                workoutRecordService.getMyRecords(userId)
        );
    }

    // ============================================================
    // 4) 단일 운동 기록 조회
    // ============================================================
    @Operation(summary = "단일 운동 기록 조회")
    @GetMapping("/{recordId}")
    public ResponseEntity<WorkoutRecordDto.ReadResponse> getRecord(
            @Parameter(hidden = true) Authentication authentication,
            @PathVariable Long recordId) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());

        return ResponseEntity.ok(
                workoutRecordService.getRecord(userId, recordId)
        );
    }
}
