package com.ssafit.domain.workout.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.user.dao.UserDao;
import com.ssafit.domain.user.entity.User;
import com.ssafit.domain.video.dao.VideoDao;
import com.ssafit.domain.video.entity.Video;
import com.ssafit.domain.workout.dao.WorkoutRecordDao;
import com.ssafit.domain.workout.dto.WorkoutRecordDto;
import com.ssafit.domain.workout.entity.WorkoutRecord;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkoutRecordServiceImpl implements WorkoutRecordService {

    private final WorkoutRecordDao workoutRecordDao;
    private final UserDao userDao;
    private final VideoDao videoDao;

    // ============================================================
    // 1) 운동 시작
    // ============================================================
    @Override
    public WorkoutRecordDto.StartResponse startWorkout(
            Long userId,
            WorkoutRecordDto.StartRequest request) {

        if (workoutRecordDao.existsOngoing(userId)) {
            throw new IllegalStateException("이미 진행 중인 운동이 있습니다.");
        }

        WorkoutRecord record = WorkoutRecord.builder()
                .userId(userId)
                .videoId(request.getVideoId())
                .startTime(LocalDateTime.now())
                .build();

        workoutRecordDao.insertRecord(record);

        return WorkoutRecordDto.StartResponse.builder()
                .recordId(record.getId())
                .startTime(record.getStartTime())
                .build();
    }

    // ============================================================
    // 2) 운동 종료 (여기서 누적 데이터 갱신)
    // ============================================================
    @Override
    public WorkoutRecordDto.EndResponse endWorkout(
            Long userId,
            Long recordId,
            WorkoutRecordDto.EndRequest request) {

        WorkoutRecord record = workoutRecordDao.selectById(recordId);

        if (record == null) {
            throw new IllegalArgumentException("운동 기록이 존재하지 않습니다.");
        }

        if (!record.getUserId().equals(userId)) {
            throw new SecurityException("권한이 없습니다.");
        }

        if (record.getEndTime() != null) {
            throw new IllegalStateException("이미 종료된 운동입니다.");
        }

        // 1. 종료 처리
        record.setEndTime(LocalDateTime.now());
        record.setUserSatisfaction(request.getUserSatisfaction());
        record.setPersonalDifficulty(request.getPersonalDifficulty());
        record.setUserMemo(request.getUserMemo());
        record.setIsSuccess(request.getIsSuccess());

        workoutRecordDao.updateRecord(record);

        // 2. 성공한 운동만 누적 반영
        if (Boolean.TRUE.equals(record.getIsSuccess())) {
            updateUserStats(userId, record);
        }

        return WorkoutRecordDto.EndResponse.builder()
                .recordId(record.getId())
                .endTime(record.getEndTime())
                .isSuccess(record.getIsSuccess())
                .build();
    }

    // ============================================================
    // 3) 누적 통계 갱신 (⭐ 핵심 메서드)
    // ============================================================
    private void updateUserStats(Long userId, WorkoutRecord record) {

        User user = userDao.selectById(userId);
        Video video = videoDao.selectVideoById(record.getVideoId());

        // =========================
        // 1) 누적 운동 시간 (영상 duration 기준)
        // =========================
        int workoutMinutes = (int) (video.getDurationSeconds() / 60);

        user.setTotalWorkoutMinutes(
                user.getTotalWorkoutMinutes() + workoutMinutes
        );

        // =========================
        // 2) 누적 소모 칼로리 (영상 calorieEstimate 기준)
        // =========================
        user.setTotalCalories(
                user.getTotalCalories() + video.getCalorieEstimate()
        );

        // =========================
        // 3) 스트릭 계산
        // =========================
        LocalDate today = record.getEndTime().toLocalDate();
        LocalDate yesterday = today.minusDays(1);

        boolean hasYesterdaySuccess =
                workoutRecordDao.existsSuccessOnDate(userId, yesterday);

        if (hasYesterdaySuccess) {
            user.setStreakDays(user.getStreakDays() + 1);
        } else {
            user.setStreakDays(1);
        }

        user.setMaxStreakDays(
                Math.max(user.getMaxStreakDays(), user.getStreakDays())
        );

        // =========================
        // 4) 총 운동 일수
        // =========================
        user.setTotalWorkoutDays(
                user.getTotalWorkoutDays() + 1
        );

        userDao.updateUserStats(user);
    }

    // ============================================================
    // 4) 내 운동 기록 조회
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public List<WorkoutRecordDto.ReadResponse> getMyRecords(Long userId) {

        return workoutRecordDao.selectByUserId(userId).stream()
                .map(r -> WorkoutRecordDto.ReadResponse.builder()
                        .recordId(r.getId())
                        .videoId(r.getVideoId())
                        .startTime(r.getStartTime())
                        .endTime(r.getEndTime())
                        .userSatisfaction(r.getUserSatisfaction())
                        .personalDifficulty(r.getPersonalDifficulty())
                        .userMemo(r.getUserMemo())
                        .isSuccess(r.getIsSuccess())
                        .build())
                .collect(Collectors.toList());
    }

    // ============================================================
    // 5) 단일 운동 기록 조회
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public WorkoutRecordDto.ReadResponse getRecord(Long userId, Long recordId) {

        WorkoutRecord record = workoutRecordDao.selectById(recordId);

        if (!record.getUserId().equals(userId)) {
            throw new SecurityException("권한이 없습니다.");
        }

        return WorkoutRecordDto.ReadResponse.builder()
                .recordId(record.getId())
                .videoId(record.getVideoId())
                .startTime(record.getStartTime())
                .endTime(record.getEndTime())
                .userSatisfaction(record.getUserSatisfaction())
                .personalDifficulty(record.getPersonalDifficulty())
                .userMemo(record.getUserMemo())
                .isSuccess(record.getIsSuccess())
                .build();
    }
}
