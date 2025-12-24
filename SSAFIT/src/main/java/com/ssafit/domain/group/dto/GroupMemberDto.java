package com.ssafit.domain.group.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class GroupMemberDto {

    // ============================================================
    // 1) 그룹 가입 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @Builder //@AllArgsConstructor
    public static class JoinRequest {
        // 지금은 groupId가 URL path로 들어오므로 보통 비움
    }

    // ============================================================
    // 2) 그룹 가입 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class JoinResponse {
        private Long groupId;
        private Long userId;
        private LocalDateTime joinedAt;
    }

    // ============================================================
    // 3) 그룹 탈퇴 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class LeaveResponse {
        private Long groupId;
        private Long userId;
        private Boolean left; // true
    }

    // ============================================================
    // 4) 그룹 멤버 조회 응답 (목록용)
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class MemberResponse {
        private Long userId;
        private String nickname; // User에서 join해서 가져올 예정
    }
}
