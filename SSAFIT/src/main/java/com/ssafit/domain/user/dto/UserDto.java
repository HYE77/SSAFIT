 package com.ssafit.domain.user.dto;

import lombok.*;
import java.time.LocalDate;

public class UserDto {
	
	// C : 회원 가입 요청 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateRequest {
        private String loginId;
        private String email;
        private String pwd;
        
        private String firstName;
        private String lastName;
        private String nickname;
        
        private LocalDate birthDate;
        private String gender;
        
        private Double height;
        private Double weight;
        
        private Integer goalType;
        private Boolean marketingAgreed;
    }
	
	
	// C : 회원 가입 응답 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CreateReponse {
        private String loginId;
        private String nickname;
    }
	
	
	// R : 본인 정보 조회 응답 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadMyselfResponse {
        
        private String nickname;
        
        private Double height;
        private Double weight;
        
        private Integer goalType;
        
        private Integer streakDays;
        private Integer maxStreakDays;
        private Integer totalWorkoutDays;
        private Integer totalWorkoutMinutes;
        
        private Long totalCalories;
        private Long level;
        private Long exp;
        private Long coin;
        
        private Long groupId;
    }
	
	
	// R : 다른 이용자 정보 조회 응답 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class ReadOthersResponse {
        
        private String nickname;
        
        private Integer maxStreakDays;
        private Integer totalWorkoutDays;
        private Integer totalWorkoutMinutes;
        
        private Long level;
    }
	
	
	// U : 본인 정보 수정 요청 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class UpdateRequest {
        
        private String pwd;
        private String nickname;
        
        private Double height;
        private Double weight;
        
        private Integer goalType;
        private Boolean marketingAgreed;
    }
	
	
	// U : 본인 정보 수정 응답 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class UpdateResponse {
	        
		private String nickname;
	        
		private Double height;
	    private Double weight;
	        
	    private Integer goalType;
	    private Boolean marketingAgreed;
    }
	
	
	// U : 비밀번호 변경 요청
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class ChangePasswordRequest {
	    private String currentPwd; // 현재 비밀번호
	    private String newPwd;     // 새 비밀번호
	}
	
	
	// U : 비밀번호 변경 응답
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class ChangePasswordResponse {
	    private boolean changed;
	}
	
	
	// D : 회원 탈퇴 요청 
	@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
	public static class DeleteRequest {
	        
		private String pwd;
    }
	

}
