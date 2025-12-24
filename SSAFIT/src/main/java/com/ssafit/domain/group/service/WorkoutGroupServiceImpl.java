package com.ssafit.domain.group.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.board.dao.PostDao;
import com.ssafit.domain.group.dao.GroupMemberDao;
import com.ssafit.domain.group.dao.WorkoutGroupDao;
import com.ssafit.domain.group.dto.GroupMemberDto;
import com.ssafit.domain.group.dto.WorkoutGroupDto;
import com.ssafit.domain.group.entity.WorkoutGroup;
import com.ssafit.domain.user.dao.UserDao;
import com.ssafit.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkoutGroupServiceImpl implements WorkoutGroupService {

    private final WorkoutGroupDao workoutGroupDao;
    private final GroupMemberDao groupMemberDao;
    private final UserDao userDao;
    private final PostDao postDao;

    // ==================================================
    // C : 그룹 생성
    // ==================================================
    @Override
    public WorkoutGroupDto.CreateResponse createGroup(Long userId, WorkoutGroupDto.CreateRequest request) {

        User user = userDao.selectById(userId);

        // ❗ 이미 그룹에 속해 있으면 생성 불가
        if (user.getGroupId() != null) {
            throw new IllegalStateException("이미 그룹에 가입되어 있습니다.");
        }

        WorkoutGroup group = WorkoutGroup.builder()
                .groupName(request.getGroupName())
                .masterId(userId)
                .isDeleted(false)
                .build();

        workoutGroupDao.insertGroup(group);

        // 유저를 해당 그룹에 소속
        user.setGroupId(group.getId());
        userDao.updateUserGroup(user);

        return WorkoutGroupDto.CreateResponse.builder()
                .groupId(group.getId())
                .groupName(group.getGroupName())
                .build();
    }


    // ==================================================
    // R : 그룹 ID로 단건 조회
    // ==================================================
    @Override
    @Transactional(readOnly = true)
    public WorkoutGroupDto.ReadResponse getGroup(Long groupId) {

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null) return null;

        return toReadResponse(group);
    }

    // ==================================================
    // R : 전체 그룹 조회
    // ==================================================
    @Override
    @Transactional(readOnly = true)
    public List<WorkoutGroupDto.ReadResponse> getAllGroups() {

        return workoutGroupDao.selectAll()
                .stream()
                .map(this::toReadResponse)
                .collect(Collectors.toList());
    }
    
    // ==================================================
    // U : 그룹 정보 수정 (그룹장만)
    // ==================================================
    @Override
    public WorkoutGroupDto.ReadResponse updateGroup(
            Long userId, Long groupId, WorkoutGroupDto.UpdateRequest request) {

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null) return null;

        if (!group.getMasterId().equals(userId)) {
            throw new SecurityException("그룹 수정 권한이 없습니다.");
        }

        group.setGroupName(request.getGroupName());
        workoutGroupDao.updateGroup(group);

        return toReadResponse(group);
    }

    // ==================================================
    // D : 그룹 삭제 (soft delete)
    // ==================================================
    @Override
    public void deleteGroup(Long userId, Long groupId) {

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null) return;

        if (!group.getMasterId().equals(userId)) {
            throw new SecurityException("그룹 삭제 권한이 없습니다.");
        }

        workoutGroupDao.softDelete(groupId);
    }
    
    
    // ==================================================
    // R : 그룹 메인 화면 읽어오기
    // ==================================================
    @Override
    @Transactional(readOnly = true)
    public WorkoutGroupDto.GroupMainResponse getGroupMain(Long groupId) {

        WorkoutGroup group = workoutGroupDao.selectById(groupId);
        if (group == null || Boolean.TRUE.equals(group.getIsDeleted())) {
            return null;
        }

        // 그룹장 정보
        User master = userDao.selectById(group.getMasterId());

        // 멤버 수
        int memberCount = groupMemberDao.countActiveMembers(groupId);

        // 멤버 목록
        List<GroupMemberDto.MemberResponse> members =
                groupMemberDao.selectActiveMembers(groupId);

        // 그룹 게시판 글
        List<WorkoutGroupDto.GroupMainResponse.GroupPostResponse> posts =
                postDao.selectGroupPosts(groupId);

        return WorkoutGroupDto.GroupMainResponse.builder()
                .groupId(group.getId())
                .groupName(group.getGroupName())
                .masterId(group.getMasterId())
                .masterNickname(master.getNickname())
                .memberCount(memberCount)
                .members(members)
                .posts(posts)
                .build();
    }


    private WorkoutGroupDto.ReadResponse toReadResponse(WorkoutGroup group) {
        return WorkoutGroupDto.ReadResponse.builder()
                .groupId(group.getId())
                .groupName(group.getGroupName())
                .masterId(group.getMasterId())
                .build();
    }
}
