package com.ssafit.domain.playlist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.playlist.service.PlaylistVideoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlists")
@Tag(name = "Playlist Video API", description = "플레이리스트 영상 관리 API")
public class PlaylistVideoController {

    private final PlaylistVideoService playlistVideoService;

    // ============================================================
    // 플레이리스트에 영상 추가
    // ============================================================
    @Operation(summary = "플레이리스트에 영상 추가")
    @PostMapping("/{playlistId}/videos/{videoId}")
    public ResponseEntity<Void> addVideo(
            @Parameter(hidden = true) Authentication auth,
            @PathVariable Long playlistId,
            @PathVariable Long videoId) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        playlistVideoService.addVideo(userId, playlistId, videoId);

        return ResponseEntity.ok().build();
    }

    // ============================================================
    // 플레이리스트에서 영상 제거
    // ============================================================
    @Operation(summary = "플레이리스트에서 영상 제거")
    @DeleteMapping("/{playlistId}/videos/{videoId}")
    public ResponseEntity<Void> removeVideo(
            @Parameter(hidden = true) Authentication auth,
            @PathVariable Long playlistId,
            @PathVariable Long videoId) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        playlistVideoService.removeVideo(userId, playlistId, videoId);

        return ResponseEntity.noContent().build();
    }

    // ============================================================
    // 플레이리스트 영상 ID 목록 조회
    // ============================================================
    @Operation(summary = "플레이리스트 영상 목록 조회 (ID)")
    @GetMapping("/{playlistId}/videos")
    public ResponseEntity<List<Long>> getVideos(
            @PathVariable Long playlistId) {

        return ResponseEntity.ok(
                playlistVideoService.getVideoIds(playlistId)
        );
    }
}
