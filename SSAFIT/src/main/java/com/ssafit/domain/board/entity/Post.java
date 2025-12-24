package com.ssafit.domain.board.entity;

import java.time.LocalDateTime;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Post")
public class Post {

    private Long id;

    private Long userId;
    private Long category;
    
    private Long groupId;

    private String title;
    private String content;

    private String imageUrls; // JSON 문자열

    private Long viewCnt;
    private Long likeCnt;
    private Integer commentCnt;

    private Boolean isPinned;
    private Boolean isNotice;
    private Boolean visibility;
    private Boolean deleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
