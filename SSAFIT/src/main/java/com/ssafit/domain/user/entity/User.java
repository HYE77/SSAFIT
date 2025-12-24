package com.ssafit.domain.user.entity;

import lombok.*;
import java.time.LocalDate;

import org.apache.ibatis.type.Alias;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("User")
@Schema(description="회원 Entity")
public class User {

	private Long id; // 회원 고유 id
	private String loginId; // 회원 로그인 id	
	private String email; // 회원 이메일
	private String pwd; // 회원 비밀번
	
	private String role; // 역할 (ADMIN, USER)
	
	private String profileImgUrl; // 프로필 이미지 url
	
	private String firstName; // 이름 
	private String lastName; // 성  
	private String nickname; // 닉네임 
	
	private Integer age; // 나이 
	private LocalDate birthDate; // 생년월일 
	private String gender; // 성별 (M, F)
	
	private LocalDate regDate; // 회원가입 날
	
	private Double height; // 키 
	private Double weight; // 몸무게 
	
	private Integer goalType; // 운동 목표 (1~5)
	
	private Integer streakDays; // 현재 스트릭 일 수 
	private Integer maxStreakDays; // 최장 스트릭 일 수  
	private Integer totalWorkoutDays; // 총 운동 일 수 
	private Integer totalWorkoutMinutes; // 누적 운동 시간 (분)
	
	private Long totalCalories; // 누적 소모 칼로리 
	private Long level; // 레벨 
	private Long exp; // 경험
	private Long coin; // 코인 
	
	private Long groupId; // 그룹 아이디 
	
	private Boolean marketingAgreed; // 마케팅 수신 동의 여부 
	private Boolean active; // 활성 여부 
	private LocalDate  updateDate; // 업데이트 날짜 
	
	private Integer tokenVersion;
}
