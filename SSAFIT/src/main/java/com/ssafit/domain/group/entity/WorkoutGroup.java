package com.ssafit.domain.group.entity;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("WorkoutGroup")
public class WorkoutGroup {

    private Long id;

    private String groupName;

    private Long masterId;   // 그룹장 (user.id)

    private Boolean isDeleted;
}
