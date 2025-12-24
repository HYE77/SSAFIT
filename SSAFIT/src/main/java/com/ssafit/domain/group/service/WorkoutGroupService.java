package com.ssafit.domain.group.service;

import java.util.List;

import com.ssafit.domain.group.dto.WorkoutGroupDto;

public interface WorkoutGroupService {

    WorkoutGroupDto.CreateResponse createGroup(Long userId, WorkoutGroupDto.CreateRequest request);

    WorkoutGroupDto.ReadResponse getGroup(Long groupId);

    List<WorkoutGroupDto.ReadResponse> getAllGroups();

    WorkoutGroupDto.ReadResponse updateGroup(Long userId, Long groupId, WorkoutGroupDto.UpdateRequest request);

    void deleteGroup(Long userId, Long groupId);
    
    WorkoutGroupDto.GroupMainResponse getGroupMain(Long groupId);

}
