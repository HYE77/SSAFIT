package com.ssafit.domain.playlist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafit.domain.playlist.dto.PlaylistDto;
import com.ssafit.domain.playlist.service.PlaylistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playlists")
@Tag(name = "Playlist API", description = "운동 플레이리스트 API")
public class PlaylistController {

    private final PlaylistService playlistService;

    
    @Operation(summary = "플레이리스트 생성")
    @PostMapping
    public ResponseEntity<PlaylistDto.CreateResponse> create(
            @Parameter(hidden = true) Authentication auth,
            @RequestBody PlaylistDto.CreateRequest request) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(playlistService.createPlaylist(userId, request));
    }

    
    @Operation(summary = "플레이리스트 단건 조회")
    @GetMapping("/{playlistId}")
    public ResponseEntity<PlaylistDto.ReadResponse> get(@PathVariable Long playlistId) {
        return ResponseEntity.ok(playlistService.getPlaylist(playlistId));
    }

    
    @Operation(summary = "내 플레이리스트 목록")
    @GetMapping("/me")
    public ResponseEntity<List<PlaylistDto.ReadResponse>> my(
            @Parameter(hidden = true) Authentication auth) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(playlistService.getMyPlaylists(userId));
    }

    
    @Operation(summary = "전체 플레이리스트 조회")
    @GetMapping
    public ResponseEntity<List<PlaylistDto.ReadResponse>> all() {
        return ResponseEntity.ok(playlistService.getAllPlaylists());
    }
    
    
    @Operation(summary = "플레이리스트 상세 조회 (영상 포함)")
    @GetMapping("/{playlistId}/detail")
    public ResponseEntity<PlaylistDto.DetailResponse> detail(
            @PathVariable Long playlistId) {

        PlaylistDto.DetailResponse response =
                playlistService.getPlaylistDetail(playlistId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    
    @Operation(summary = "플레이리스트 수정")
    @PutMapping("/{playlistId}")
    public ResponseEntity<PlaylistDto.ReadResponse> update(
            @Parameter(hidden = true) Authentication auth,
            @PathVariable Long playlistId,
            @RequestBody PlaylistDto.UpdateRequest request) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        return ResponseEntity.ok(
                playlistService.updatePlaylist(userId, playlistId, request)
        );
    }

    
    @Operation(summary = "플레이리스트 삭제")
    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> delete(
            @Parameter(hidden = true) Authentication auth,
            @PathVariable Long playlistId) {

        Long userId = Long.valueOf(auth.getPrincipal().toString());
        playlistService.deletePlaylist(userId, playlistId);
        return ResponseEntity.noContent().build();
    }
}
