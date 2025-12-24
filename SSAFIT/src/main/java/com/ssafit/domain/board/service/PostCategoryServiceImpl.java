package com.ssafit.domain.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafit.domain.board.dao.PostCategoryDao;
import com.ssafit.domain.board.dto.PostCategoryDto;
import com.ssafit.domain.board.entity.PostCategory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCategoryServiceImpl implements PostCategoryService {

    private final PostCategoryDao postCategoryDao;

    @Override
    public List<PostCategoryDto.ReadResponse> readAllCategories() {

        List<PostCategory> categories = postCategoryDao.selectAll();

        return categories.stream()
                .map(c -> PostCategoryDto.ReadResponse.builder()
                        .id(c.getId())
                        .category(c.getCategory())
                        .build())
                .collect(Collectors.toList());
    }
}
