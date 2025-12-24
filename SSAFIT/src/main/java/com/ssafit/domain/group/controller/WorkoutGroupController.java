package com.ssafit.domain.group.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.group.dto.WorkoutGroupDto;
import com.ssafit.domain.group.service.WorkoutGroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@Tag(name = "Workout Group API", description = "운동 그룹 API")
public class WorkoutGroupController {

    private final WorkoutGroupService workoutGroupService;

    @Operation(summary = "운동 그룹 생성")
    @PostMapping
    public ResponseEntity<WorkoutGroupDto.CreateResponse> create(
            @Parameter(hidden = true) Authentication auth,
            @RequestBody WorkoutGroupDto.CreateRequest request) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(workoutGroupService.createGroup(userId, request));
    }

    @Operation(summary = "운동 그룹 단건 조회")
    @GetMapping("/{groupId}")
    public ResponseEntity<WorkoutGroupDto.ReadResponse> get(@PathVariable Long groupId) {
        return ResponseEntity.ok(workoutGroupService.getGroup(groupId));
    }


    @Operation(summary = "전체 운동 그룹 조회")
    @GetMapping
    public ResponseEntity<List<WorkoutGroupDto.ReadResponse>> all() {
        return ResponseEntity.ok(workoutGroupService.getAllGroups());
    }


    @Operation(summary = "운동 그룹 수정")
    @PutMapping("/{groupId}")
    public ResponseEntity<WorkoutGroupDto.ReadResponse> update(
            @Parameter(hidden = true) Authentication auth,
            @PathVariable Long groupId,
            @RequestBody WorkoutGroupDto.UpdateRequest request) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(
                workoutGroupService.updateGroup(userId, groupId, request)
        );
    }

    @Operation(summary = "운동 그룹 삭제")
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> delete(
            @Parameter(hidden = true) Authentication auth,
            @PathVariable Long groupId) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        workoutGroupService.deleteGroup(userId, groupId);
        return ResponseEntity.noContent().build();
    }
    
    
    @Operation(summary = "그룹 메인 화면 조회")
    @GetMapping("/{groupId}/detail")
    public ResponseEntity<WorkoutGroupDto.GroupMainResponse> groupMain(
            @PathVariable Long groupId) {

        WorkoutGroupDto.GroupMainResponse response =
                workoutGroupService.getGroupMain(groupId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

}
