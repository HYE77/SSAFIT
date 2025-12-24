package com.ssafit.domain.video.entity;

import java.time.LocalDate;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Video")
@Schema(description = "운동 영상 Entity")
public class Video {

    private Long id;                    // 영상 ID (PK)

    private String title;               // 영상 제목
    private String description;         // 영상 설명

    private String videoUrl;            // 영상 URL
    private String thumbnailUrl;        // 썸네일 URL

    private Long durationSeconds;       // 영상 길이(초)

    private Integer difficulty;         // 난이도 (1~5)
    private String targetPart;           // 운동 부위 (전신, 상체 등)

    private Integer calorieEstimate;    // 예상 소모 칼로리
    private String category;             // 카테고리

    private Long viewCnt;               // 조회수
    private Long likeCnt;               // 좋아요 수
    private Long reviewCnt;             // 리뷰 수

    private Long createdBy;             // 생성자 (admin/user id)

    private LocalDate createdAt;    // 생성 일시
    private LocalDate updatedAt;    // 수정 일시

    private Boolean visibility;         // 공개 여부
    private Boolean deleted;            // 삭제 여부 (is_deleted → deleted)
}
