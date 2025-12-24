package com.ssafit.domain.video.dao;

import java.util.List;

import com.ssafit.domain.video.dto.VideoReviewSummary;
import com.ssafit.domain.video.entity.VideoReview;

public interface VideoReviewDao {

    int insertReview(VideoReview review);

    VideoReview selectById(Long id);

    List<VideoReview> selectByVideoId(Long videoId);

    int updateReview(VideoReview review);

    int deleteReview(Long id);
    
    VideoReviewSummary selectSummaryByVideoId(Long videoId);
}
