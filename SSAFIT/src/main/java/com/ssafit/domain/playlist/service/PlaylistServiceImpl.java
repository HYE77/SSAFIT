package com.ssafit.domain.playlist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.playlist.dao.PlaylistDao;
import com.ssafit.domain.playlist.dao.PlaylistVideoDao;
import com.ssafit.domain.playlist.dto.PlaylistDto;
import com.ssafit.domain.playlist.entity.Playlist;
import com.ssafit.domain.video.dao.VideoDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistDao playlistDao;
    private final PlaylistVideoDao playlistVideoDao;
    private final VideoDao videoDao;

    // ============================================================
    // C: 플레이리스트 생성
    // ============================================================
    @Override
    public PlaylistDto.CreateResponse createPlaylist(Long userId, PlaylistDto.CreateRequest request) {

        Playlist playlist = Playlist.builder()
                .publisherId(userId)
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        playlistDao.insertPlaylist(playlist);

        return PlaylistDto.CreateResponse.builder()
                .playlistId(playlist.getId())
                .title(playlist.getTitle())
                .build();
    }

    
    // ============================================================
    // R: 플레이리스트 단건 조회
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public PlaylistDto.ReadResponse getPlaylist(Long playlistId) {

        Playlist playlist = playlistDao.selectById(playlistId);
        if (playlist == null) return null;

        return toReadResponse(playlist);
    }

    
    // ============================================================
    // R: 내가 만든 플레이리스트 목록
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public List<PlaylistDto.ReadResponse> getMyPlaylists(Long userId) {

        return playlistDao.selectByPublisherId(userId)
                .stream()
                .map(this::toReadResponse)
                .collect(Collectors.toList());
    }

    
    // ============================================================
    // R: 전체 플레이리스트 조회
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public List<PlaylistDto.ReadResponse> getAllPlaylists() {

        return playlistDao.selectAll()
                .stream()
                .map(this::toReadResponse)
                .collect(Collectors.toList());
    }
    
    
    // ============================================================
    // R: 플레이리스트 ID로 플레이리스트 상세 조회
    // ============================================================
    @Override
    @Transactional(readOnly = true)
    public PlaylistDto.DetailResponse getPlaylistDetail(Long playlistId) {

        Playlist playlist = playlistDao.selectById(playlistId);
        if (playlist == null) return null;

        // 1. playlist에 포함된 videoId 목록
        List<Long> videoIds = playlistVideoDao.selectVideoIds(playlistId);

        // 2. video 정보 조회
        List<PlaylistDto.DetailResponse.VideoSummary> videos =
                videoIds.isEmpty()
                ? List.of()
                : videoDao.selectByIds(videoIds).stream()
                    .map(v -> PlaylistDto.DetailResponse.VideoSummary.builder()
                            .id(v.getId())
                            .title(v.getTitle())
                            .thumbnailUrl(v.getThumbnailUrl())
                            .durationSeconds(v.getDurationSeconds())
                            .calorieEstimate(v.getCalorieEstimate())
                            .difficulty(v.getDifficulty())
                            .build()
                    )
                    .toList();

        return PlaylistDto.DetailResponse.builder()
                .playlistId(playlist.getId())
                .title(playlist.getTitle())
                .description(playlist.getDescription())
                .publisherId(playlist.getPublisherId())
                .videos(videos)
                .build();
    }


    
    // ============================================================
    // U: 플레이리스트 수정
    // ============================================================
    @Override
    public PlaylistDto.ReadResponse updatePlaylist(
            Long userId, Long playlistId, PlaylistDto.UpdateRequest request) {

        Playlist playlist = playlistDao.selectById(playlistId);
        if (playlist == null) return null;

        if (!playlist.getPublisherId().equals(userId)) {
            throw new SecurityException("수정 권한이 없습니다.");
        }

        playlist.setTitle(request.getTitle());
        playlist.setDescription(request.getDescription());

        playlistDao.updatePlaylist(playlist);

        return toReadResponse(playlist);
    }

    
    // ============================================================
    // D: 플레이리스트 삭제
    // ============================================================
    @Override
    public void deletePlaylist(Long userId, Long playlistId) {

        Playlist playlist = playlistDao.selectById(playlistId);
        if (playlist == null) return;

        if (!playlist.getPublisherId().equals(userId)) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }

        playlistDao.deletePlaylist(playlistId);
    }

    private PlaylistDto.ReadResponse toReadResponse(Playlist playlist) {
        return PlaylistDto.ReadResponse.builder()
                .playlistId(playlist.getId())
                .title(playlist.getTitle())
                .description(playlist.getDescription())
                .publisherId(playlist.getPublisherId())
                .build();
    }
}
