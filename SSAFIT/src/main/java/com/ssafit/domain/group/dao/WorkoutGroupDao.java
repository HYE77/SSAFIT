package com.ssafit.domain.group.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssafit.domain.group.entity.WorkoutGroup;

public interface WorkoutGroupDao {

    // C: 그룹 생성
    int insertGroup(WorkoutGroup group);

    // R: 그룹 단건 조회
    WorkoutGroup selectById(@Param("id") Long id);

    // R: 전체 그룹 조회 (탐색용)
    List<WorkoutGroup> selectAll();

    // U: 그룹 수정 (그룹장만)
    int updateGroup(WorkoutGroup group);
    
    // U: 그룹장 수정 (위임)
    int updateGroupMaster(WorkoutGroup group);

    // D: 그룹 삭제 (Soft Delete)
    int softDelete(@Param("id") Long id);
}
