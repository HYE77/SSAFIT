package com.ssafit.domain.achievement.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    private Long id;
    private String missionName;
    private String description;
}
