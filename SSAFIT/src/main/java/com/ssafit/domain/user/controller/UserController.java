package com.ssafit.domain.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.domain.user.dto.UserDto;
import com.ssafit.domain.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "회원 관련 REST API")
public class UserController {

    private final UserService userService;

    // ============================================================
    // C: 회원가입
    // ============================================================
    @Operation(summary = "회원가입", description = "새로운 회원을 등록합니다.")
    @PostMapping
    public ResponseEntity<UserDto.CreateReponse> createUser(
            @RequestBody UserDto.CreateRequest request) {

        return ResponseEntity.ok(userService.createUser(request));
    }

    // ============================================================
    // R: 본인 정보 조회 — JWT 필요
    // ============================================================
    @Operation(summary = "내 정보 조회", description = "JWT 토큰을 기반으로 본인 정보를 조회합니다.")
    @GetMapping("/me")
    public ResponseEntity<UserDto.ReadMyselfResponse> readMyself(
            @RequestAttribute("userId") Long userId) {

        UserDto.ReadMyselfResponse response =
                userService.readMyself(userId);

        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }


    // ============================================================
    // R: 다른 이용자 정보 조회 (Public)
    // ============================================================
    @Operation(summary = "다른 회원 정보 조회", description = "특정 회원의 공개 정보를 조회합니다.")
    @GetMapping("/{loginId}")
    public ResponseEntity<UserDto.ReadOthersResponse> readOthers(
            @PathVariable String loginId) {

        UserDto.ReadOthersResponse response = userService.readOthers(loginId);
        if (response == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(response);
    }

    // ============================================================
    // R: 전체 회원 조회 (관리자 only)
    // ============================================================
    @Operation(summary = "전체 회원 조회", description = "모든 회원 목록을 조회합니다. (ADMIN)")
    @GetMapping
    public ResponseEntity<List<UserDto.ReadOthersResponse>> readAllUsers() {

        return ResponseEntity.ok(userService.readAllUsers());
    }

    // ============================================================
    // U: 회원 정보 수정 — JWT 필요
    // ============================================================
    @Operation(summary = "내 정보 수정", description = "JWT 토큰 기반으로 본인의 정보를 수정합니다.")
    @PutMapping("/me")
    public ResponseEntity<UserDto.UpdateResponse> updateUser(
            Authentication authentication,
            @RequestBody UserDto.UpdateRequest request) {

        // SecurityContext에서 loginId 추출
        String loginId = (String) authentication.getPrincipal();

        UserDto.UpdateResponse response = userService.updateUser(loginId, request);
        if (response == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(response);
    }
    
    // ============================================================
    // U: 비밀번호 변경
    // ============================================================
    @Operation(
    	    summary = "비밀번호 변경",
    	    description = "JWT 인증된 사용자의 비밀번호를 변경합니다."
    	)
    	@PutMapping("/me/password")
    	public ResponseEntity<UserDto.ChangePasswordResponse> changePassword(
    	        @Parameter(hidden = true)
    	        @RequestAttribute("loginId") String loginId,
    	        @RequestBody UserDto.ChangePasswordRequest request) {

    	    return ResponseEntity.ok(
    	        userService.changePassword(loginId, request)
    	    );
    	}


    // ============================================================
    // D: 회원 탈퇴 — JWT 필요
    // ============================================================
    @Operation(summary = "회원 탈퇴", description = "JWT 기반으로 본인을 탈퇴 처리합니다.")
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(
            Authentication authentication,
            @RequestBody UserDto.DeleteRequest request) {

        // SecurityContext에서 loginId 추출
        String loginId = (String) authentication.getPrincipal();

        userService.deleteUser(loginId, request);
        return ResponseEntity.noContent().build();
    }
}
