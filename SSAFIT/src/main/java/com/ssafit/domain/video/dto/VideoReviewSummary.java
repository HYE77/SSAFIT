package com.ssafit.domain.video.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VideoReviewSummary {

    private Double avgSatisfaction;
    private Double avgDifficulty;
    private Long reviewCnt;
}
