package com.ssafit.domain.playlist.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PlaylistDto {

    // ============================================================
    // 1) 플레이리스트 생성 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {
        private String title;
        private String description;
    }

    // ============================================================
    // 2) 플레이리스트 생성 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResponse {
        private Long playlistId;
        private String title;
    }

    // ============================================================
    // 3) 플레이리스트 조회 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {
        private Long playlistId;
        private String title;
        private String description;
        private Long publisherId;
    }

    // ============================================================
    // 4) 플레이리스트 수정 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateRequest {
        private String title;
        private String description;
    }
    
	// ============================================================
	// 5) 플레이리스트 상세 조회 응답 (영상 포함)
	// ============================================================
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class DetailResponse {
	
	    private Long playlistId;
	    private String title;
	    private String description;
	    private Long publisherId;
	
	    private List<VideoSummary> videos;
	
	    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	    public static class VideoSummary {
	        private Long id;
	        private String title;
	        private String thumbnailUrl;
	        private Long durationSeconds;
	        private Integer calorieEstimate;
	        private Integer difficulty;
	    }
	}

}
