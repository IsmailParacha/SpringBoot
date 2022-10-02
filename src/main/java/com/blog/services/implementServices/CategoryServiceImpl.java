package com.blog.services.implementServices;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.entities.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repository.CategoryRepo;
import com.blog.services.CategoryServices;

@Service
public class CategoryServiceImpl implements CategoryServices {
    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto catdto) {
        Category cat = this.modelMapper.map(catdto, Category.class);
        Category addcat = this.catRepo.save(cat);
        return this.modelMapper.map(addcat, CategoryDto.class);
    }

    @Override
    public CategoryDto upadateCategory(CategoryDto catdto, Integer id) {
        Category cat = this.catRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        cat.setTitle(catdto.getTitle());
        cat.setDescription(catdto.getDescription());
        Category updatecat = this.catRepo.save(cat);
        return this.modelMapper.map(updatecat, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category cat = this.catRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.catRepo.findAll();
        List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
        return catDtos;
    }

    @Override
    public void deleteCategory(Integer id) {
        Category cat = this.catRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        this.catRepo.delete(cat);

    }

}
