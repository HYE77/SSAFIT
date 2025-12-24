package com.ssafit.domain.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.user.dao.UserDao;
import com.ssafit.domain.user.dto.UserDto;
import com.ssafit.domain.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    // ============================================================
    // C: 회원가입
    // ============================================================
    @Override
    public UserDto.CreateReponse createUser(UserDto.CreateRequest request) {
    	
    	String encodedPwd = passwordEncoder.encode(request.getPwd());
    	
        User user = User.builder()
                .loginId(request.getLoginId())
                .email(request.getEmail())
                .pwd(encodedPwd)  // 비밀번호 암호화 미적용
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .nickname(request.getNickname())
                .birthDate(request.getBirthDate())
                .gender(request.getGender()) 
                .height(request.getHeight())
                .weight(request.getWeight())
                .goalType(request.getGoalType())
                .marketingAgreed(request.getMarketingAgreed())
                .active(true)
                .build();

        userDao.insertUser(user);

        return UserDto.CreateReponse.builder()
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .build();
    }


    // ============================================================
    // R: 본인 정보 조회
    // ============================================================
    @Override
    public UserDto.ReadMyselfResponse readMyself(String loginId) {

        User user = userDao.selectUserbyId(loginId);
        if (user == null) {
            return null;
        }

        return UserDto.ReadMyselfResponse.builder()
                .nickname(user.getNickname())
                .height(user.getHeight() != null ? user.getHeight() : 0)
                .weight(user.getWeight() != null ? user.getWeight() : 0)
                .goalType(user.getGoalType() != null ? user.getGoalType() : 0)
                .streakDays(user.getStreakDays() != null ? user.getStreakDays() : 0)
                .maxStreakDays(user.getMaxStreakDays() != null ? user.getMaxStreakDays() : 0)
                .totalWorkoutDays(user.getTotalWorkoutDays() != null ? user.getTotalWorkoutDays() : 0)
                .totalWorkoutMinutes(user.getTotalWorkoutMinutes() != null ? user.getTotalWorkoutMinutes() : 0)
                .totalCalories(user.getTotalCalories() != null ? user.getTotalCalories() : 0)
                .level(user.getLevel() != null ? user.getLevel() : 0)
                .exp(user.getExp() != null ? user.getExp() : 0)
                .coin(user.getCoin() != null ? user.getCoin() : 0)
                .groupId(user.getGroupId() != null ? user.getGroupId() : 0)
                .build();
    }
    
    
    @Override
    public UserDto.ReadMyselfResponse readMyself(Long userId) {

        User user = userDao.selectById(userId); // userId 기준
        if (user == null) return null;

        return UserDto.ReadMyselfResponse.builder()
                .nickname(user.getNickname())
                .height(user.getHeight())
                .weight(user.getWeight())
                .goalType(user.getGoalType())
                .streakDays(user.getStreakDays())
                .maxStreakDays(user.getMaxStreakDays())
                .totalWorkoutDays(user.getTotalWorkoutDays())
                .totalWorkoutMinutes(user.getTotalWorkoutMinutes())
                .totalCalories(user.getTotalCalories())
                .level(user.getLevel())
                .exp(user.getExp())
                .coin(user.getCoin())
                .groupId(user.getGroupId())
                .build();
    }



    // ============================================================
    // R: 다른 이용자 정보 조회
    // ============================================================
    @Override
    public UserDto.ReadOthersResponse readOthers(String loginId) {

        User user = userDao.selectUserbyId(loginId);
        if (user == null) {
            return null;
        }

        return UserDto.ReadOthersResponse.builder()
                .nickname(user.getNickname())
                .maxStreakDays(user.getMaxStreakDays() != null ? user.getMaxStreakDays() : 0)
                .totalWorkoutDays(user.getTotalWorkoutDays() != null ? user.getTotalWorkoutDays() : 0)
                .totalWorkoutMinutes(user.getTotalWorkoutMinutes() != null ? user.getTotalWorkoutMinutes() : 0)
                .level(user.getLevel() != null ? user.getLevel() : 0)
                .build();
    }


    // ============================================================
    // R: 전체 이용자 목록 조회
    // ============================================================
    @Override
    public List<UserDto.ReadOthersResponse> readAllUsers() {

        List<User> list = userDao.selectAll();

        return list.stream()
                .map(u -> UserDto.ReadOthersResponse.builder()
                        .nickname(u.getNickname())
                        .maxStreakDays(u.getMaxStreakDays() != null ? u.getMaxStreakDays() : 0)
                        .totalWorkoutDays(u.getTotalWorkoutDays() != null ? u.getTotalWorkoutDays() : 0)
                        .totalWorkoutMinutes(u.getTotalWorkoutMinutes() != null ? u.getTotalWorkoutMinutes() : 0)
                        .level(u.getLevel() != null ? u.getLevel() : 0)
                        .build()
                )
                .collect(Collectors.toList());
    }


    // ============================================================
    // U: 본인 정보 수정
    // ============================================================
    @Override
    public UserDto.UpdateResponse updateUser(String loginId, UserDto.UpdateRequest request) {

        User user = userDao.selectUserbyId(loginId);
        if (user == null) {
            return null;
        }
        
        if (request.getPwd() != null && !request.getPwd().isBlank()) {
            user.setPwd(request.getPwd());
        }

        user.setNickname(request.getNickname());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        user.setGoalType(request.getGoalType());
        user.setMarketingAgreed(request.getMarketingAgreed());

        userDao.updateUser(user);

        return UserDto.UpdateResponse.builder()
                .nickname(user.getNickname())
                .height(user.getHeight() != null ? user.getHeight() : 0)
                .weight(user.getWeight() != null ? user.getWeight() : 0)
                .goalType(user.getGoalType() != null ? user.getGoalType() : 0)
                .marketingAgreed(user.getMarketingAgreed() != null ? user.getMarketingAgreed() : false)
                .build();
    }
    
    
    // ============================================================
    // U: 비밀번호 변경
    // ============================================================
    @Override
    public UserDto.ChangePasswordResponse changePassword(
            String loginId,
            UserDto.ChangePasswordRequest request) {

        // 1️. 사용자 조회
        User user = userDao.selectUserbyId(loginId);
        if (user == null) {
            throw new RuntimeException("사용자가 존재하지 않습니다.");
        }

        // 2️. 현재 비밀번호 검증
        if (!passwordEncoder.matches(request.getCurrentPwd(), user.getPwd())) {
            throw new RuntimeException("현재 비밀번호가 올바르지 않습니다.");
        }

        // 3️. 새 비밀번호 암호화
        String encodedNewPwd = passwordEncoder.encode(request.getNewPwd());

        // 4️. DB 반영
        userDao.updatePassword(loginId, encodedNewPwd);

        return UserDto.ChangePasswordResponse.builder()
                .changed(true)
                .build();
    }



    // ============================================================
    // D: 회원 탈퇴
    // ============================================================
    @Override
    public void deleteUser(String loginId, UserDto.DeleteRequest request) {

        User user = userDao.selectUserbyId(loginId);
        if (user == null) {
            return;
        }

        // 비밀번호 추후 암호화 필요
        if (!user.getPwd().equals(request.getPwd())) {
            throw new RuntimeException("비밀번호가 올바르지 않습니다.");
        }

        userDao.deleteUser(loginId);
    }
}
