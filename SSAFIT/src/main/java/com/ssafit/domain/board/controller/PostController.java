package com.ssafit.domain.board.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafit.domain.board.dto.PostDetailDto;
import com.ssafit.domain.board.dto.PostDto;
import com.ssafit.domain.board.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "Post API", description = "게시글 관련 REST API")
public class PostController {

    private final PostService postService;

    // ============================================================
    // C: 게시글 작성 (JWT 필요)
    // ============================================================
    @Operation(
        summary = "게시글 작성",
        description = "JWT 인증된 사용자가 게시글을 작성합니다."
    )
    @PostMapping
    public ResponseEntity<PostDto.CreateResponse> createPost(
            @Parameter(hidden = true) Authentication authentication,
            @RequestBody PostDto.CreateRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return ResponseEntity.ok(postService.createPost(userId, request));
    }

    // ============================================================
    // R: 게시글 단건 조회 (Public)
    // ============================================================
    @Operation(
        summary = "게시글 단건 조회",
        description = "게시글 ID를 기준으로 게시글 상세 정보를 조회합니다."
    )
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto.ReadResponse> readPost(
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long postId) {

        return ResponseEntity.ok(postService.readPost(postId));
    }

    // ============================================================
    // R: 게시글 목록 조회 (Public)
    // ============================================================
    @Operation(
        summary = "게시글 목록 조회",
        description = "전체 게시글 목록을 최신순으로 조회합니다."
    )
    @GetMapping
    public ResponseEntity<List<PostDto.ReadResponse>> readAllPosts() {

        return ResponseEntity.ok(postService.readAllPosts());
    }
    
	// ============================================================
	// R: 게시글 + 댓글 동시 조회 (Public)
	// ============================================================
	@Operation(
	    summary = "게시글 상세 조회 (댓글 포함)",
	    description = "게시글과 해당 게시글의 댓글 목록을 함께 조회합니다."
	)
	@GetMapping("/{postId}/detail")
	public ResponseEntity<PostDetailDto.ReadResponse> readPostDetail(
	        @Parameter(description = "게시글 ID", example = "1")
	        @PathVariable Long postId) {
	
	    PostDetailDto.ReadResponse response =
	            postService.readPostWithComments(postId);
	
	    if (response == null) {
	        return ResponseEntity.notFound().build();
	    }
	
	    return ResponseEntity.ok(response);
	}


    // ============================================================
    // U: 게시글 수정 (JWT 필요)
    // ============================================================
    @Operation(
        summary = "게시글 수정",
        description = "JWT 인증된 작성자가 본인의 게시글을 수정합니다."
    )
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updatePost(
            @Parameter(hidden = true) Authentication authentication,
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long postId,
            @RequestBody PostDto.UpdateRequest request) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        postService.updatePost(userId, postId, request);
        return ResponseEntity.ok().build();
    }

    // ============================================================
    // D: 게시글 삭제 (Soft Delete, JWT 필요)
    // ============================================================
    @Operation(
        summary = "게시글 삭제",
        description = "JWT 인증된 작성자가 본인의 게시글을 삭제합니다. (Soft Delete)"
    )
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDto.DeleteResponse> deletePost(
            @Parameter(hidden = true) Authentication authentication,
            @Parameter(description = "게시글 ID", example = "1")
            @PathVariable Long postId) {

        Long userId = Long.valueOf(authentication.getPrincipal().toString());
        return ResponseEntity.ok(postService.deletePost(userId, postId));
    }
}
