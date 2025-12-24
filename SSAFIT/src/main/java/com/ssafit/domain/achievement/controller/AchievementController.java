package com.ssafit.domain.achievement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.achievement.dto.AchievementDto;
import com.ssafit.domain.achievement.service.AchievementService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
@Tag(name = "Achievement API", description = "업적(미션) 관련 API")
public class AchievementController {

    private final AchievementService achievementService;

    @Operation(summary = "내 업적 목록 조회")
    @GetMapping
    public ResponseEntity<List<AchievementDto.AchievementResponse>> myAchievements(
            @RequestAttribute("userId") Long userId
    ) {
        return ResponseEntity.ok(
                achievementService.getMyAchievements(userId)
        );
    }

    @Operation(summary = "업적 달성 처리")
    @PostMapping("/{missionId}/achieve")
    public ResponseEntity<AchievementDto.AchieveResponse> achieve(
            @RequestAttribute("userId") Long userId,
            @PathVariable Long missionId
    ) {
        return ResponseEntity.ok(
                achievementService.achieve(userId, missionId)
        );
    }
}
