package com.ssafit.domain.achievement.service;

import java.util.List;
import com.ssafit.domain.achievement.dto.AchievementDto;

public interface AchievementService {

    // 내 업적 목록
    List<AchievementDto.AchievementResponse> getMyAchievements(Long userId);

    // 업적 달성 처리
    AchievementDto.AchieveResponse achieve(Long userId, Long missionId);
}
