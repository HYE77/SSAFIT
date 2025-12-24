package com.ssafit.domain.board.dto;

import lombok.*;

public class PostCategoryDto {

    // ============================================================
    // 카테고리 조회 응답 DTO
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {
        private Long id;
        private String category;
    }
}
