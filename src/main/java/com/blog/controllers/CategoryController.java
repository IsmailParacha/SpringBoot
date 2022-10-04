package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.blog.payload.*;
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
        return new ResponseEntity<>(createcategory, HttpStatus.CREATED);

    }

    // Update
    @PutMapping("/updateCategory/{catid}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto catdto, @PathVariable Integer catid) {
        CategoryDto updateCategory = this.categoryServices.upadateCategory(catdto, catid);
        return ResponseEntity.ok(updateCategory);
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
