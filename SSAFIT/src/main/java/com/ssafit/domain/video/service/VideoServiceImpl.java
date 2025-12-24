package com.ssafit.domain.video.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.video.dao.VideoDao;
import com.ssafit.domain.video.dao.VideoReviewDao;
import com.ssafit.domain.video.dto.VideoDto;
import com.ssafit.domain.video.dto.VideoReviewDto;
import com.ssafit.domain.video.dto.VideoReviewSummary;
import com.ssafit.domain.video.entity.Video;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoServiceImpl implements VideoService {

    private final VideoDao videoDao;
    private final VideoReviewDao videoReviewDao;

    // ============================================================
    // C: 영상 등록
    // ============================================================
    @Override
    public VideoDto.CreateResponse createVideo(VideoDto.CreateRequest request) {

        Video video = Video.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .videoUrl(request.getVideoUrl())
                .thumbnailUrl(request.getThumbnailUrl())
                .durationSeconds(request.getDurationSeconds())
                .difficulty(request.getDifficulty())
                .targetPart(request.getTargetPart())
                .calorieEstimate(request.getCalorieEstimate())
                .category(request.getCategory())
                .createdBy(request.getCreatedBy())
                .viewCnt(0L)
                .likeCnt(0L)
                .reviewCnt(0L)
                .visibility(true)
                .deleted(false)
                .createdAt(LocalDate.now())
                .updatedAt(LocalDate.now())
                .build();

        videoDao.insertVideo(video);

        return VideoDto.CreateResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .visibility(video.getVisibility())
                .createdAt(video.getCreatedAt())
                .build();
    }

    // ============================================================
    // R: 영상 단건 조회
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public VideoDto.ReadResponse readVideo(Long videoId) {

        Video video = videoDao.selectVideoById(videoId);
        if (video == null || Boolean.TRUE.equals(video.getDeleted())) {
            return null;
        }

        // read-only 메서드에서는 쓰기 제거
        return toReadResponse(video);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increaseViewCnt(Long videoId) {
        videoDao.increaseViewCnt(videoId);
    }



    // ============================================================
    // R: 전체 영상 목록 조회 (사용자용)
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public List<VideoDto.ReadResponse> readAllVideos() {

        return videoDao.selectAllVisibleVideos()
                .stream()
                .map(this::toReadResponse)
                .collect(Collectors.toList());
    }

    // ============================================================
    // U: 영상 정보 수정
    // ============================================================
    @Override
    public VideoDto.UpdateResponse updateVideo(Long videoId, VideoDto.UpdateRequest request) {

        Video video = videoDao.selectVideoById(videoId);
        if (video == null || Boolean.TRUE.equals(video.getDeleted())) {
            return null;
        }

        video.setTitle(request.getTitle());
        video.setDescription(request.getDescription());
        video.setThumbnailUrl(request.getThumbnailUrl());
        video.setDifficulty(request.getDifficulty());
        video.setTargetPart(request.getTargetPart());
        video.setCalorieEstimate(request.getCalorieEstimate());
        video.setCategory(request.getCategory());
        video.setVisibility(request.getVisibility());
        video.setUpdatedAt(LocalDate.now());

        videoDao.updateVideo(video);

        return VideoDto.UpdateResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .visibility(video.getVisibility())
                .updatedAt(video.getUpdatedAt())
                .build();
    }

    // ============================================================
    // D: 영상 삭제 (Soft Delete)
    // ============================================================
    @Override
    public VideoDto.DeleteResponse deleteVideo(Long videoId) {

        Video video = videoDao.selectVideoById(videoId);
        if (video == null || Boolean.TRUE.equals(video.getDeleted())) {
            return null;
        }

        videoDao.deleteVideo(videoId);

        return VideoDto.DeleteResponse.builder()
                .id(videoId)
                .deleted(true)
                .build();
    }
 
    
    // ============================================================
    // R: 영상 리뷰 요약 (평균 만족도/난이도)
    // ============================================================
    @Override
    public VideoDto.ReviewSummaryResponse getReviewSummary(Long videoId) {

        VideoReviewSummary summary =
                videoReviewDao.selectSummaryByVideoId(videoId);

        // 리뷰가 하나도 없는 경우 대비
        if (summary == null || summary.getReviewCnt() == 0) {
            return VideoDto.ReviewSummaryResponse.builder()
                    .videoId(videoId)
                    .avgSatisfaction(0.0)
                    .avgDifficulty(0.0)
                    .reviewCnt(0L)
                    .build();
        }

        return VideoDto.ReviewSummaryResponse.builder()
                .videoId(videoId)
                .avgSatisfaction(summary.getAvgSatisfaction())
                .avgDifficulty(summary.getAvgDifficulty())
                .reviewCnt(summary.getReviewCnt())
                .build();
    }



    // ============================================================
    // Entity → DTO 변환 메서드
    // ============================================================
    private VideoDto.ReadResponse toReadResponse(Video video) {

        return VideoDto.ReadResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .videoUrl(video.getVideoUrl())
                .thumbnailUrl(video.getThumbnailUrl())
                .durationSeconds(video.getDurationSeconds())
                .difficulty(video.getDifficulty())
                .targetPart(video.getTargetPart())
                .calorieEstimate(video.getCalorieEstimate())
                .category(video.getCategory())
                .viewCnt(video.getViewCnt())
                .likeCnt(video.getLikeCnt())
                .reviewCnt(video.getReviewCnt())
                .createdAt(video.getCreatedAt())
                .updatedAt(video.getUpdatedAt())
                .visibility(video.getVisibility())
                .build();
    }
    
    // ============================================================
    // R : 영상 상세 정보 조회 (상세 정보, 리뷰 요약, 리뷰 목록)
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public VideoDto.VideoDetailResponse getVideoDetail(Long videoId) {

        // 1. 영상 기본 정보 조회
        Video video = videoDao.selectVideoById(videoId);
        if (video == null) {
            throw new RuntimeException("영상이 존재하지 않습니다.");
        }

        // 2. 리뷰 요약 조회
        VideoReviewSummary summary =
                videoReviewDao.selectSummaryByVideoId(videoId);

        VideoDto.ReviewSummaryResponse reviewSummary =
                (summary == null || summary.getReviewCnt() == 0)
                ? VideoDto.ReviewSummaryResponse.builder()
                    .videoId(videoId)
                    .avgSatisfaction(0.0)
                    .avgDifficulty(0.0)
                    .reviewCnt(0L)
                    .build()
                : VideoDto.ReviewSummaryResponse.builder()
                    .videoId(videoId)
                    .avgSatisfaction(summary.getAvgSatisfaction())
                    .avgDifficulty(summary.getAvgDifficulty())
                    .reviewCnt(summary.getReviewCnt())
                    .build();

        // 3. 리뷰 목록 조회
        List<VideoReviewDto.ReadResponse> reviews =
                videoReviewDao.selectByVideoId(videoId).stream()
                    .map(r -> VideoReviewDto.ReadResponse.builder()
                            .id(r.getId())
                            .userId(r.getUserId())
                            .satisfaction(r.getSatisfaction())
                            .difficulty(r.getDifficulty())
                            .content(r.getContent())
                            .createdTime(r.getCreatedTime())
                            .build())
                    .toList();

        // 4. 최종 응답 조립
        return VideoDto.VideoDetailResponse.builder()
                .id(video.getId())
                .title(video.getTitle())
                .description(video.getDescription())
                .videoUrl(video.getVideoUrl())
                .thumbnailUrl(video.getThumbnailUrl())
                .durationSeconds(video.getDurationSeconds())
                .difficulty(video.getDifficulty())
                .targetPart(video.getTargetPart())
                .calorieEstimate(video.getCalorieEstimate())
                .category(video.getCategory())
                .viewCnt(video.getViewCnt())
                .likeCnt(video.getLikeCnt())
                .reviewCnt(reviewSummary.getReviewCnt())
                .visibility(video.getVisibility())
                .reviewSummary(reviewSummary)
                .reviews(reviews)
                .build();
    }

}
