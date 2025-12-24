package com.ssafit.domain.achievement.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.ssafit.domain.achievement.entity.Mission;
import com.ssafit.domain.achievement.entity.MissionRecord;

public interface AchievementDao {

    // 전체 업적 목록
    List<Mission> selectAllMissions();

    // 유저 업적 달성 기록
    List<MissionRecord> selectRecordsByUser(@Param("userId") Long userId);

    // 특정 업적 이미 달성했는지
    boolean existsMissionRecord(
            @Param("userId") Long userId,
            @Param("missionId") Long missionId
    );

    // 업적 달성 기록 추가
    int insertMissionRecord(MissionRecord record);
}
