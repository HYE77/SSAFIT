package com.ssafit.domain.video.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.domain.video.dto.VideoDto;
import com.ssafit.domain.video.service.VideoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
@Tag(name = "Video API", description = "운동 영상 관련 API")
public class VideoController {

    private final VideoService videoService;

    // ============================================================
    // C: 영상 등록 (ADMIN / JWT 필요)
    // ============================================================
    @Operation(
        summary = "영상 등록",
        description = "관리자가 새로운 운동 영상을 등록합니다."
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<VideoDto.CreateResponse> createVideo(
            @RequestBody VideoDto.CreateRequest request,
            @RequestAttribute("loginId") String loginId
    ) {
        // 실제로는 loginId → adminId 변환 필요
        // 현재 구조에서는 CreateRequest에 createdBy 포함
        VideoDto.CreateResponse response = videoService.createVideo(request);
        return ResponseEntity.ok(response);
    }

    // ============================================================
    // R: 영상 단건 조회 (공개)
    // ============================================================
    @Operation(
        summary = "영상 상세 조회",
        description = "영상 ID로 운동 영상 상세 정보를 조회합니다."
    )
    @GetMapping("/{videoId}")
    public ResponseEntity<VideoDto.ReadResponse> readVideo(@PathVariable Long videoId) {

        VideoDto.ReadResponse response = videoService.readVideo(videoId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        // 조회수 증가 (쓰기 트랜잭션)
        videoService.increaseViewCnt(videoId);

        return ResponseEntity.ok(response);
    }


    // ============================================================
    // R: 전체 영상 목록 조회 (공개)
    // ============================================================
    @Operation(
        summary = "전체 영상 목록 조회",
        description = "공개된 운동 영상 목록을 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<VideoDto.ReadResponse>> readAllVideos() {
        return ResponseEntity.ok(videoService.readAllVideos());
    }
    

    // ============================================================
    // U: 영상 정보 수정 (ADMIN / JWT 필요)
    // ============================================================
    @Operation(
        summary = "영상 정보 수정",
        description = "관리자가 운동 영상 정보를 수정합니다."
    )
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{videoId}")
    public ResponseEntity<VideoDto.UpdateResponse> updateVideo(
            @PathVariable Long videoId,
            @RequestBody VideoDto.UpdateRequest request,
            @RequestAttribute("loginId") String loginId
    ) {
        VideoDto.UpdateResponse response =
                videoService.updateVideo(videoId, request);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // ============================================================
    // D: 영상 삭제 (ADMIN / JWT 필요, Soft Delete)
    // ============================================================
    @Operation(
        summary = "영상 삭제",
        description = "관리자가 운동 영상을 삭제(Soft Delete)합니다."
    )
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{videoId}")
    public ResponseEntity<VideoDto.DeleteResponse> deleteVideo(
            @PathVariable Long videoId,
            @RequestAttribute("loginId") String loginId
    ) {
        VideoDto.DeleteResponse response =
                videoService.deleteVideo(videoId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    
    
    // ============================================================
    // R: 영상 리뷰 요약 정보 조회
    // ============================================================
    @Operation(summary = "영상 리뷰 요약 정보 조회")
    @GetMapping("/{videoId}/review-summary")
    public ResponseEntity<VideoDto.ReviewSummaryResponse> getReviewSummary(
            @PathVariable Long videoId) {

        return ResponseEntity.ok(videoService.getReviewSummary(videoId));
    }
    
    
    // ============================================================
    // R: 영상 상세 정보 조회 (상세 정보, 리뷰 요약, 리뷰 목록)
    // ============================================================
    @Operation(summary = "영상 상세 조회 (리뷰 요약 + 리뷰 목록 포함)")
    @GetMapping("/{videoId}/detail")
    public ResponseEntity<VideoDto.VideoDetailResponse> getVideoDetail(
            @PathVariable Long videoId) {

        return ResponseEntity.ok(videoService.getVideoDetail(videoId));
    }


}
