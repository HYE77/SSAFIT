package com.ssafit.domain.user.dao;

import java.util.List;

import com.ssafit.domain.user.entity.User;

public interface UserDao {
	
	// 회원 정보 추가
	public int insertUser(User user);
	
	// 회원 조회
	public User selectUserbyId(String loginId);
	
	// PK로 회원 조회
	public User selectById(Long id);
	
	// 전체 회원 조회
	public List<User> selectAll();
	
	// 현재 스트릭 조회
	int selectCurrentStreak(Long userId);
	
	// 회원 정보 수정
	public int updateUser(User user);
	
	// 회원 그룹 수정
	public int updateUserGroup(User user);
	
	// 회원 비밀번호 변경
	int updatePassword(String loginId, String pwd);
	
	// 회원 통계 업데이트
	public int updateUserStats(User user);
	
	// 회원 정보 삭제
	public int deleteUser(String loginId);
	
	// 로그아웃
	public void increaseTokenVersion(Long userId);

}
