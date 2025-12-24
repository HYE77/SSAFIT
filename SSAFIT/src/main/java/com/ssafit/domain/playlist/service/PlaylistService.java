package com.ssafit.domain.playlist.service;

import java.util.List;

import com.ssafit.domain.playlist.dto.PlaylistDto;

public interface PlaylistService {

    // C: 플레이리스트 생성
    PlaylistDto.CreateResponse createPlaylist(Long userId, PlaylistDto.CreateRequest request);

    // R: 플레이리스트 단건 조회
    PlaylistDto.ReadResponse getPlaylist(Long playlistId);

    // R: 내가 만든 플레이리스트 목록
    List<PlaylistDto.ReadResponse> getMyPlaylists(Long userId);

    // R: 전체 플레이리스트 조회
    List<PlaylistDto.ReadResponse> getAllPlaylists();
    
    // R: 플레이리스트 ID로 상세 조회
    PlaylistDto.DetailResponse getPlaylistDetail(Long playlistId);

    // U: 플레이리스트 수정
    PlaylistDto.ReadResponse updatePlaylist(Long userId, Long playlistId, PlaylistDto.UpdateRequest request);

    // D: 플레이리스트 삭제
    void deletePlaylist(Long userId, Long playlistId);
}
