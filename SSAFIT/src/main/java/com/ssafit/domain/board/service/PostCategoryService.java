package com.ssafit.domain.board.service;

import java.util.List;
import com.ssafit.domain.board.dto.PostCategoryDto;

public interface PostCategoryService {

    List<PostCategoryDto.ReadResponse> readAllCategories();
}
