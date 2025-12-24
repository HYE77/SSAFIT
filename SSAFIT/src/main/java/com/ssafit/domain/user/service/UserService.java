package com.ssafit.domain.user.service;

import java.util.List;

import com.ssafit.domain.user.dto.UserDto;

public interface UserService {

    // -------------------------
    // C: 회원가입
    // -------------------------
    UserDto.CreateReponse createUser(UserDto.CreateRequest request);


    // -------------------------
    // R: 본인 정보 조회
    // -------------------------
    UserDto.ReadMyselfResponse readMyself(String loginId);
    
    UserDto.ReadMyselfResponse readMyself(Long userId);



    // -------------------------
    // R: 다른 사용자의 정보 조회
    // -------------------------
    UserDto.ReadOthersResponse readOthers(String loginId);


    // -------------------------
    // R: 전체 회원 목록 조회 (옵션)
    // -------------------------
    List<UserDto.ReadOthersResponse> readAllUsers();


    // -------------------------
    // U: 본인 정보 수정
    // loginId는 인증에서 받아오므로 request에는 포함되지 않음
    // -------------------------
    UserDto.UpdateResponse updateUser(String loginId, UserDto.UpdateRequest request);
    
    
    // -------------------------
    // U: 비밀번호 변경
    // -------------------------
    UserDto.ChangePasswordResponse changePassword(
    	    String loginId,
    	    UserDto.ChangePasswordRequest request
    	);



    // -------------------------
    // D: 회원 탈퇴
    // loginId는 인증에서 받아오고,
    // DeleteRequest에는 pwd 검증만 수행
    // -------------------------
    void deleteUser(String loginId, UserDto.DeleteRequest request);
}
