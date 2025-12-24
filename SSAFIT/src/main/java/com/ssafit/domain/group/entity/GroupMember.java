package com.ssafit.domain.group.entity;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("GroupMember")
public class GroupMember {

    private Long id;

    private Long groupId;
    private Long userId;

    private LocalDateTime joinedAt;

    private Boolean isActive;
}
