package com.ssafit.domain.group.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.domain.group.dto.GroupMemberDto;
import com.ssafit.domain.group.entity.GroupMember;

public interface GroupMemberDao {

    // ============================================================
    // C: 가입 (insert)
    // ============================================================
    int insert(GroupMember member);

    // ============================================================
    // R: 특정 유저의 "활성" 멤버십 조회 (user는 하나의 그룹만 가능)
    // ============================================================
    GroupMember selectActiveByUserId(@Param("userId") Long userId);

    // ============================================================
    // R: 그룹 활성 멤버 목록 조회 (nickname 포함)
    // ============================================================
    List<GroupMemberDto.MemberResponse> selectActiveMembers(@Param("groupId") Long groupId);

    // ============================================================
    // R: 그룹 인원 수
    // ============================================================
    int countActiveMembers(@Param("groupId") Long groupId);
    
    // ============================================================
    // R: 그룹-유저 데이터로 특정 멤버 존재
    // ============================================================
    GroupMember selectActiveByGroupAndUser(@Param("groupId") Long groupId, @Param("userId") Long userId);

    // ============================================================
    // U: 탈퇴 (soft leave)
    // ============================================================
    int deactivate(@Param("groupId") Long groupId, @Param("userId") Long userId);

    // ============================================================
    // U: 재가입 처리 (기존 row 활성화)
    // ============================================================
    int reactivate(@Param("groupId") Long groupId, @Param("userId") Long userId);
}
