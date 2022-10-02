package com.blog.services;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryServices {
    CategoryDto createCategory(CategoryDto catdto);

    CategoryDto upadateCategory(CategoryDto catdto, Integer id);

    CategoryDto getUserById(Integer id);

    List<CategoryDto> getAll();

    void deleteCategoory(Integer id);
}
