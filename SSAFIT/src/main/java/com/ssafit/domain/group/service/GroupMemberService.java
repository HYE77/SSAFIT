package com.ssafit.domain.group.service;

import com.ssafit.domain.group.dto.GroupMemberDto;

public interface GroupMemberService {

    // 그룹 가입
    GroupMemberDto.JoinResponse joinGroup(Long userId, Long groupId);

    // 그룹 탈퇴
    GroupMemberDto.LeaveResponse leaveGroup(Long userId, Long groupId);

    // 그룹장 위임
    void delegateMaster(Long userId, Long groupId, Long targetUserId);
}