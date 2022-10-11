package com.blog.controllers;

import javax.validation.Valid;

import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.payload.*;
import com.blog.services.FileServices;
import com.blog.services.PostServices;

@RestController
@RequestMapping("/api/")
public class postController {
    @Autowired
    private PostServices postservice;

    @Autowired
    private FileServices fileService;

    // Mention declaration path from properties file
    @Value("${project.image}")
    private String path;

    @PostMapping("user:{userId}/category:{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto, @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createPost = this.postservice.createPost(postdto, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    @GetMapping("/user:{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postservice.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/category:{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId) {
        List<PostDto> posts = this.postservice.getPostByCategory(catId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.PAGE_NO, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse allpost = this.postservice.getAllPost(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(allpost, HttpStatus.OK);
    }

    @GetMapping("/posts/id:{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer id) {
        PostDto post = this.postservice.getPostById(id);
        return new ResponseEntity<PostDto>(post, HttpStatus.OK);
    }

    @DeleteMapping("/post/id:{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        this.postservice.deletePost(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post successfully deleted", true), HttpStatus.OK);
    }

    // searching
    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
        List<PostDto> result = this.postservice.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }

    // uploadImage
    @PostMapping("/post/image/PostId/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
            @PathVariable Integer postId) throws IOException {
        PostDto postDto = this.postservice.getPostById(postId);
        String fileName = this.fileService.uploadIMage(path, image);

        postDto.setImageName(fileName);
        PostDto updatedPost = this.postservice.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

    }
}
