package com.ssafit.domain.playlist.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.playlist.dao.PlaylistDao;
import com.ssafit.domain.playlist.dao.PlaylistVideoDao;
import com.ssafit.domain.playlist.entity.Playlist;
import com.ssafit.domain.playlist.entity.PlaylistVideo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistVideoServiceImpl implements PlaylistVideoService {

    private final PlaylistDao playlistDao;
    private final PlaylistVideoDao playlistVideoDao;

    // ============================================================
    // 플레이리스트에 영상 추가
    // ============================================================
    @Override
    public void addVideo(Long userId, Long playlistId, Long videoId) {

        // 1. 플레이리스트 존재 + 소유자 체크
        Playlist playlist = playlistDao.selectById(playlistId);
        if (playlist == null) {
            throw new IllegalArgumentException("플레이리스트가 존재하지 않습니다.");
        }

        if (!playlist.getPublisherId().equals(userId)) {
            throw new SecurityException("플레이리스트 수정 권한이 없습니다.");
        }

        // 2. 연결 테이블 insert
        PlaylistVideo pv = PlaylistVideo.builder()
                .playlistId(playlistId)
                .videoId(videoId)
                .build();

        playlistVideoDao.insert(pv);
    }

    // ============================================================
    // 플레이리스트에서 영상 제거 (Soft Delete)
    // ============================================================
    @Override
    public void removeVideo(Long userId, Long playlistId, Long videoId) {

        Playlist playlist = playlistDao.selectById(playlistId);
        if (playlist == null) {
            throw new IllegalArgumentException("플레이리스트가 존재하지 않습니다.");
        }

        if (!playlist.getPublisherId().equals(userId)) {
            throw new SecurityException("플레이리스트 수정 권한이 없습니다.");
        }

        playlistVideoDao.softDelete(playlistId, videoId);
    }

    // ============================================================
    // 플레이리스트 영상 목록 조회 (ID만)
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public List<Long> getVideoIds(Long playlistId) {
        return playlistVideoDao.selectVideoIds(playlistId);
    }
}
