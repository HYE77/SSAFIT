package com.ssafit.domain.group.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class WorkoutGroupDto {

    // ============================================================
    // 1) 그룹 생성 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {
        private String groupName;
    }

    // ============================================================
    // 2) 그룹 생성 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateResponse {
        private Long groupId;
        private String groupName;
    }

    // ============================================================
    // 3) 그룹 조회 응답
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadResponse {
        private Long groupId;
        private String groupName;
        private Long masterId;
    }

    // ============================================================
    // 4) 그룹 수정 요청
    // ============================================================
    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateRequest {
        private String groupName;
    }
    
	// ============================================================
	// 5) 그룹 메인 화면 응답 DTO
	// ============================================================
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class GroupMainResponse {
	
	    private Long groupId;
	    private String groupName;
	
	    private Long masterId;
	    private String masterNickname;
	
	    private int memberCount;
	
	    private List<GroupMemberDto.MemberResponse> members;
	
	    private List<GroupPostResponse> posts;
	
	    // ------------------------------------------------------------
	    // 그룹 게시판 글 요약 DTO
	    // ------------------------------------------------------------
	    @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	    public static class GroupPostResponse {
	        private Long postId;
	        private String title;
	        private String writerNickname;
	        private int commentCount;
	        private Long viewCount;
	    }
	}

}
