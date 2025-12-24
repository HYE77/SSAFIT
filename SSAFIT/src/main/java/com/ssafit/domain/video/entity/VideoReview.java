package com.ssafit.domain.video.entity;

import java.time.LocalDateTime;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("VideoReview")
public class VideoReview {

    private Long id;

    private Long userId;
    private Long videoId;

    private Integer satisfaction; // 만족도
    private Integer difficulty;   // 체감 난이도

    private String content;

    private LocalDateTime createdTime;
    private LocalDateTime updatedAt;
}
