package com.ssafit.domain.workout.entity;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Alias("WorkoutRecord")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class WorkoutRecord {

    private Long id;
    private Long userId;
    private Long videoId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer userSatisfaction;
    private Integer personalDifficulty;
    private String userMemo;

    private Boolean isSuccess;
}
