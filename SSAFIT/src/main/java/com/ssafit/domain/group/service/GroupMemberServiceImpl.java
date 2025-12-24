package com.ssafit.domain.group.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.group.dao.GroupMemberDao;
import com.ssafit.domain.group.dao.WorkoutGroupDao;
import com.ssafit.domain.group.dto.GroupMemberDto;
import com.ssafit.domain.group.entity.GroupMember;
import com.ssafit.domain.group.entity.WorkoutGroup;
import com.ssafit.domain.user.dao.UserDao;
import com.ssafit.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberDao groupMemberDao;
    private final WorkoutGroupDao workoutGroupDao;
    private final UserDao userDao;

    // ============================================================
    // 1️. 그룹 가입
    // ============================================================
    @Override
    public GroupMemberDto.JoinResponse joinGroup(Long userId, Long groupId) {

        User user = userDao.selectById(userId);
        if (user.getGroupId() != null) {
            throw new IllegalStateException("이미 그룹에 가입되어 있습니다.");
        }

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null || Boolean.TRUE.equals(group.getIsDeleted())) {
            throw new IllegalArgumentException("존재하지 않는 그룹입니다.");
        }

        // 기존 이력 존재 여부 확인
        GroupMember history = groupMemberDao.selectActiveByUserId(userId);
        
        if (history != null) {
            throw new IllegalStateException("이미 그룹에 가입되어 있습니다.");
        }


        // 가입 처리
        GroupMember member = GroupMember.builder()
                .groupId(groupId)
                .userId(userId)
                .isActive(true)
                .build();

        groupMemberDao.insert(member);

        // user 테이블 동기화
        user.setGroupId(groupId);
        userDao.updateUserGroup(user);

        return GroupMemberDto.JoinResponse.builder()
                .groupId(groupId)
                .userId(userId)
                .joinedAt(member.getJoinedAt())
                .build();
    }

    // ============================================================
    // 2️. 그룹 탈퇴
    // ============================================================
    @Override
    public GroupMemberDto.LeaveResponse leaveGroup(Long userId, Long groupId) {

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null) {
            throw new IllegalArgumentException("존재하지 않는 그룹입니다.");
        }

        // 그룹장은 탈퇴 불가
        if (group.getMasterId().equals(userId)) {
            throw new IllegalStateException("그룹장은 탈퇴할 수 없습니다. 위임 후 탈퇴하세요.");
        }

        // 멤버십 비활성화
        int updated = groupMemberDao.deactivate(groupId, userId);
        if (updated == 0) {
            throw new IllegalStateException("이미 탈퇴했거나 가입된 멤버가 아닙니다.");
        }

        // user 테이블 동기화
        User user = userDao.selectById(userId);
        user.setGroupId(null);
        userDao.updateUserGroup(user);

        return GroupMemberDto.LeaveResponse.builder()
                .groupId(groupId)
                .userId(userId)
                .left(true)
                .build();
    }

    // ============================================================
    // 3️. 그룹장 위임
    // ============================================================
    @Override
    public void delegateMaster(Long userId, Long groupId, Long targetUserId) {

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null) {
            throw new IllegalArgumentException("존재하지 않는 그룹입니다.");
        }

        // 그룹장만 가능
        if (!group.getMasterId().equals(userId)) {
            throw new SecurityException("그룹장만 위임할 수 있습니다.");
        }

        // 대상 유저가 같은 그룹의 활성 멤버인지 확인
        GroupMember targetMember =
                groupMemberDao.selectActiveByGroupAndUser(groupId, targetUserId);

        if (targetMember == null) {
            throw new IllegalArgumentException("같은 그룹의 멤버만 위임할 수 있습니다.");
        }


        // 그룹장 변경
        group.setMasterId(targetUserId);
        workoutGroupDao.updateGroupMaster(group);
    }
}
