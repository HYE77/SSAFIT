package com.ssafit.domain.board.dao;

import java.util.List;
import com.ssafit.domain.board.entity.PostCategory;

public interface PostCategoryDao {

    List<PostCategory> selectAll();
}
