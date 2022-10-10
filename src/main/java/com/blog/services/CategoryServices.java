package com.blog.services;

import java.util.List;
import com.blog.payload.CategoryDto;

public interface CategoryServices {
    CategoryDto createCategory(CategoryDto catdto);

    CategoryDto upadateCategory(CategoryDto catdto, Integer catid);

    CategoryDto getCategoryById(Integer catid);

    List<CategoryDto> getAllCategory(Integer pageNo,Integer pageSize,String sortBy,String sortDir);

    void deleteCategory(Integer catid);
}
