package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.blog.config.AppConstants;
import com.blog.payload.*;
import com.blog.services.CategoryServices;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    // Create
    @PostMapping("/addCategory/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catdto) {
        CategoryDto createcategory = this.categoryServices.createCategory(catdto);
        return new ResponseEntity<>(createcategory, HttpStatus.CREATED);

    }

    // Update
    @PutMapping("/updateCategory/{catid}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catdto, @PathVariable Integer catid) {
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
    public ResponseEntity<List<CategoryDto>> getAllCategories(@RequestParam(value = "pageNo",defaultValue = AppConstants.PAGE_NO,required = false) Integer pageNo,
    @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
    @RequestParam(value = "sortBy",defaultValue = AppConstants.C_SORT_BY,required = false) String sortBy,@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir) {
        return ResponseEntity.ok(this.categoryServices.getAllCategory(pageNo,pageSize,sortBy,sortDir));
    }

    // Get Single Category
    @GetMapping("/getCategory/{catid}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer catid) {
        return ResponseEntity.ok(this.categoryServices.getCategoryById(catid));
    }

}
