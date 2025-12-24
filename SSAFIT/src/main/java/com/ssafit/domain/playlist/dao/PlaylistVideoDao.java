package com.ssafit.domain.playlist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafit.domain.playlist.entity.PlaylistVideo;

public interface PlaylistVideoDao {

    // ============================================================
    // C: 플레이리스트에 영상 추가
    // ============================================================
    int insert(PlaylistVideo playlistVideo);

    // ============================================================
    // R: 플레이리스트에 포함된 영상 ID 목록
    // ============================================================
    List<Long> selectVideoIds(@Param("playlistId") Long playlistId);

    // ============================================================
    // D: 플레이리스트에서 영상 제거 (Soft Delete)
    // ============================================================
    int softDelete(
            @Param("playlistId") Long playlistId,
            @Param("videoId") Long videoId
    );
}
