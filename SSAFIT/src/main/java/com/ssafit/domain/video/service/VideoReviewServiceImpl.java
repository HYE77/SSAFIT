package com.ssafit.domain.video.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.video.dao.VideoReviewDao;
import com.ssafit.domain.video.dto.VideoReviewDto;
import com.ssafit.domain.video.entity.VideoReview;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoReviewServiceImpl implements VideoReviewService {

    private final VideoReviewDao videoReviewDao;

    @Override
    public VideoReviewDto.CreateResponse createReview(
            Long userId, Long videoId, VideoReviewDto.CreateRequest request) {

        VideoReview review = VideoReview.builder()
                .userId(userId)
                .videoId(videoId)
                .satisfaction(request.getSatisfaction())
                .difficulty(request.getDifficulty())
                .content(request.getContent())
                .build();

        videoReviewDao.insertReview(review);

        return VideoReviewDto.CreateResponse.builder()
                .id(review.getId())
                .createdTime(review.getCreatedTime())
                .build();
    }

    @Override
    public List<VideoReviewDto.ReadResponse> readReviewsByVideo(Long videoId) {

        return videoReviewDao.selectByVideoId(videoId).stream()
                .map(r -> VideoReviewDto.ReadResponse.builder()
                        .id(r.getId())
                        .userId(r.getUserId())
                        .satisfaction(r.getSatisfaction())
                        .difficulty(r.getDifficulty())
                        .content(r.getContent())
                        .createdTime(r.getCreatedTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void updateReview(
            Long userId, Long reviewId, VideoReviewDto.UpdateRequest request) {

        VideoReview review = videoReviewDao.selectById(reviewId);

        if (review == null || !review.getUserId().equals(userId)) {
            throw new RuntimeException("리뷰 수정 권한이 없습니다.");
        }

        review.setSatisfaction(request.getSatisfaction());
        review.setDifficulty(request.getDifficulty());
        review.setContent(request.getContent());

        videoReviewDao.updateReview(review);
    }

    @Override
    public VideoReviewDto.DeleteResponse deleteReview(
            Long userId, Long reviewId) {

        VideoReview review = videoReviewDao.selectById(reviewId);

        if (review == null || !review.getUserId().equals(userId)) {
            throw new RuntimeException("리뷰 삭제 권한이 없습니다.");
        }

        videoReviewDao.deleteReview(reviewId);

        return VideoReviewDto.DeleteResponse.builder()
                .id(reviewId)
                .deleted(true)
                .build();
    }
}
