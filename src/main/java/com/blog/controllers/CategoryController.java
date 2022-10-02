package com.blog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.CategoryDto;
import com.blog.services.CategoryServices;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryServices categoryServices;

    // Create
    @PostMapping("/addCategory/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catdto) {
        CategoryDto createcategory = this.categoryServices.createCategory(catdto);
        return new ResponseEntity<CategoryDto>(createcategory, HttpStatus.CREATED);

    }

    // Update
    @PutMapping("/updatecategory/{id}")
    public ResponseEntity<CategoryDto> updatecategory(@RequestBody CategoryDto catdto, @PathVariable Integer id) {
        CategoryDto updateCategory = this.categoryServices.upadateCategory(catdto, id);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }
    // Delete
    // Get Single category
    // Get All Categories

}
