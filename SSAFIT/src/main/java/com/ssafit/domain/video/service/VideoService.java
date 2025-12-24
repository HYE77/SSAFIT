package com.ssafit.domain.video.service;

import java.util.List;

import com.ssafit.domain.video.dto.VideoDto;

public interface VideoService {

    // ============================================================
    // C: 영상 등록
    // ============================================================
    VideoDto.CreateResponse createVideo(VideoDto.CreateRequest request);


    // ============================================================
    // R: 영상 단건 조회 (순수 조회)
    // ============================================================
    VideoDto.ReadResponse readVideo(Long videoId);


    // ============================================================
    // ETC: 조회수 증가 (쓰기 트랜잭션)
    // ============================================================
    void increaseViewCnt(Long videoId);


    // ============================================================
    // R: 전체 영상 목록 조회 (사용자용)
    // ============================================================
    List<VideoDto.ReadResponse> readAllVideos();


    // ============================================================
    // U: 영상 정보 수정
    // ============================================================
    VideoDto.UpdateResponse updateVideo(Long videoId, VideoDto.UpdateRequest request);


    // ============================================================
    // D: 영상 삭제 (Soft Delete)
    // ============================================================
    VideoDto.DeleteResponse deleteVideo(Long videoId);
    
    // ============================================================
    // R: 영상 리뷰 요약(평균 만족도/난이도)
    // ============================================================
    VideoDto.ReviewSummaryResponse getReviewSummary(Long videoId);
    
    // ============================================================
    // R: 영상 상세 정보 조회 (상세 내용, 리뷰 요약, 리뷰 목록)
    // ============================================================
    VideoDto.VideoDetailResponse getVideoDetail(Long videoId);
}
