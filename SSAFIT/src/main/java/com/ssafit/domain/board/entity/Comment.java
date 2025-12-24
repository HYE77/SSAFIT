package com.ssafit.domain.board.entity;

import java.time.LocalDateTime;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Comment")
public class Comment {

    private Long id;

    private Long postId;   // 게시글 ID
    private Long userId;   // 작성자 ID

    private String content;

    private Long likeCnt;
    private Boolean deleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
