package com.ssafit.domain.achievement.entity;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionRecord {

    private Long id;
    private Long missionId;
    private Long userId;
    private LocalDateTime achievedAt;
}
