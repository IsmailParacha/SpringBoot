package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;
import com.blog.services.CategoryServices;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    // Create
    @PostMapping("/addCategory/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto catdto) {
        CategoryDto createcategory = this.categoryServices.createCategory(catdto);
        return new ResponseEntity<CategoryDto>(createcategory, HttpStatus.CREATED);

    }

    // Update
    @PutMapping("/updateCategory/{catid}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto catdto, @PathVariable Integer catid) {
        CategoryDto updateCategory = this.categoryServices.upadateCategory(catdto, catid);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    //
    @DeleteMapping("/deleteCategory/{catid}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catid) {
        this.categoryServices.deleteCategory(catid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    // Get All categories
    @GetMapping("/getAllCategories/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(this.categoryServices.getAllCategory());
    }

    // Get Single Category
    @GetMapping("/getCategory/{catid}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer catid) {
        return ResponseEntity.ok(this.categoryServices.getCategoryById(catid));
    }

}
