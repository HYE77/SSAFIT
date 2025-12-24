package com.ssafit.domain.playlist.service;

import java.util.List;

public interface PlaylistVideoService {

    // 플레이리스트에 영상 추가
    void addVideo(Long userId, Long playlistId, Long videoId);

    // 플레이리스트에서 영상 제거 (Soft Delete)
    void removeVideo(Long userId, Long playlistId, Long videoId);

    // 플레이리스트에 포함된 영상 ID 목록 조회
    List<Long> getVideoIds(Long playlistId);
}
