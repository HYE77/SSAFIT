package com.ssafit.domain.board.dto;

import java.util.List;

import lombok.*;

public class PostDetailDto {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {

        private PostDto.ReadResponse post;
        private List<CommentDto.ReadResponse> comments;
    }
}
