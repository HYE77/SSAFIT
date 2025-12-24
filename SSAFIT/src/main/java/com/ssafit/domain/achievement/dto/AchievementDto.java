package com.ssafit.domain.achievement.dto;

import java.time.LocalDateTime;
import lombok.*;

public class AchievementDto {

    // 업적 목록 + 달성 여부
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class AchievementResponse {
        private Long missionId;
        private String missionName;
        private String description;
        private boolean achieved;
        private LocalDateTime achievedAt;
    }

    // 업적 달성 처리 응답
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class AchieveResponse {
        private Long missionId;
        private boolean achieved;
    }
}
