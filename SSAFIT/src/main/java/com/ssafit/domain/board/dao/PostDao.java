package com.ssafit.domain.board.dao;

import java.util.List;

import com.ssafit.domain.board.entity.Post;
import com.ssafit.domain.group.dto.WorkoutGroupDto;

public interface PostDao {

    int insertPost(Post post);

    Post selectById(Long id);

    List<Post> selectAll();

    int updatePost(Post post);

    int softDeletePost(Long id);

    int increaseViewCnt(Long id);
    
    List<WorkoutGroupDto.GroupMainResponse.GroupPostResponse> selectGroupPosts(Long groupId);

}
