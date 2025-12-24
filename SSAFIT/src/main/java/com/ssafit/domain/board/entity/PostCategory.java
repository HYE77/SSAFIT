package com.ssafit.domain.board.entity;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("PostCategory")
public class PostCategory {

    private Long id;
    private String category;
}
