package com.ssafit.domain.video.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.video.dto.VideoReviewDto;
import com.ssafit.domain.video.service.VideoReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/videos/{videoId}/reviews")
@Tag(name = "Video Review API", description = "영상 리뷰 관련 REST API")
public class VideoReviewController {

    private final VideoReviewService videoReviewService;

    // ============================================================
    // C: 리뷰 작성 (JWT 필요)
    // ============================================================
    @Operation(summary = "영상 리뷰 작성")
    @PostMapping
    public ResponseEntity<VideoReviewDto.CreateResponse> createReview(
            @Parameter(hidden = true) Authentication authentication,
            @PathVariable Long videoId,
            @RequestBody VideoReviewDto.CreateRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return ResponseEntity.ok(
                videoReviewService.createReview(userId, videoId, request)
        );
    }

    // ============================================================
    // R: 리뷰 목록 조회 (Public)
    // ============================================================
    @Operation(summary = "영상 리뷰 목록 조회")
    @GetMapping
    public ResponseEntity<List<VideoReviewDto.ReadResponse>> readReviews(
            @PathVariable Long videoId) {

        return ResponseEntity.ok(
                videoReviewService.readReviewsByVideo(videoId)
        );
    }

    // ============================================================
    // U: 리뷰 수정 (JWT 필요)
    // ============================================================
    @Operation(summary = "영상 리뷰 수정")
    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateReview(
            @Parameter(hidden = true) Authentication authentication,
            @PathVariable Long reviewId,
            @RequestBody VideoReviewDto.UpdateRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        videoReviewService.updateReview(userId, reviewId, request);
        return ResponseEntity.ok().build();
    }

    // ============================================================
    // D: 리뷰 삭제 (JWT 필요)
    // ============================================================
    @Operation(summary = "영상 리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<VideoReviewDto.DeleteResponse> deleteReview(
            @Parameter(hidden = true) Authentication authentication,
            @PathVariable Long reviewId) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return ResponseEntity.ok(
                videoReviewService.deleteReview(userId, reviewId)
        );
    }
}
