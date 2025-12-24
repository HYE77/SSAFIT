package com.ssafit.domain.video.service;

import java.util.List;
import com.ssafit.domain.video.dto.VideoReviewDto;

public interface VideoReviewService {

    VideoReviewDto.CreateResponse createReview(
            Long userId, Long videoId, VideoReviewDto.CreateRequest request
    );

    List<VideoReviewDto.ReadResponse> readReviewsByVideo(Long videoId);

    void updateReview(
            Long userId, Long reviewId, VideoReviewDto.UpdateRequest request
    );

    VideoReviewDto.DeleteResponse deleteReview(
            Long userId, Long reviewId
    );
}
