package com.ssafit.domain.achievement.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.achievement.dao.AchievementDao;
import com.ssafit.domain.achievement.dto.AchievementDto;
import com.ssafit.domain.achievement.entity.Mission;
import com.ssafit.domain.achievement.entity.MissionRecord;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AchievementServiceImpl implements AchievementService {

    private final AchievementDao achievementDao;

    @Override
    @Transactional(readOnly = true)
    public List<AchievementDto.AchievementResponse> getMyAchievements(Long userId) {

        List<Mission> missions = achievementDao.selectAllMissions();
        List<MissionRecord> records = achievementDao.selectRecordsByUser(userId);

        Map<Long, MissionRecord> recordMap =
                records.stream().collect(Collectors.toMap(
                        MissionRecord::getMissionId,
                        r -> r
                ));

        return missions.stream()
                .map(m -> AchievementDto.AchievementResponse.builder()
                        .missionId(m.getId())
                        .missionName(m.getMissionName())
                        .description(m.getDescription())
                        .achieved(recordMap.containsKey(m.getId()))
                        .achievedAt(
                                recordMap.containsKey(m.getId())
                                        ? recordMap.get(m.getId()).getAchievedAt()
                                        : null
                        )
                        .build()
                )
                .toList();
    }

    @Override
    public AchievementDto.AchieveResponse achieve(Long userId, Long missionId) {

        if (achievementDao.existsMissionRecord(userId, missionId)) {
            return new AchievementDto.AchieveResponse(missionId, false);
        }

        achievementDao.insertMissionRecord(
                MissionRecord.builder()
                        .missionId(missionId)
                        .userId(userId)
                        .build()
        );

        return new AchievementDto.AchieveResponse(missionId, true);
    }
}
