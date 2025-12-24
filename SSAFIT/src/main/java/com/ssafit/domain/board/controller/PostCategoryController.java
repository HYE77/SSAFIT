package com.ssafit.domain.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.board.dto.PostCategoryDto;
import com.ssafit.domain.board.service.PostCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/categories")
@Tag(name = "Post Category API", description = "게시글 카테고리 조회 API")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;

    // ============================================================
    // R: 게시글 카테고리 목록 조회 (Public)
    // ============================================================
    @Operation(
        summary = "게시글 카테고리 목록 조회",
        description = "게시글 작성/필터링에 사용되는 카테고리 목록을 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<PostCategoryDto.ReadResponse>> readAllCategories() {

        return ResponseEntity.ok(
                postCategoryService.readAllCategories()
        );
    }
}
