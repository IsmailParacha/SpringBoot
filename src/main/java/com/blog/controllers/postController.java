package com.blog.controllers;

import javax.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.PostDto;
import com.blog.services.PostServices;


@RestController
@RequestMapping("/api/")
public class postController {
    @Autowired
    private PostServices postservice;

    @PostMapping("user:{userId}/category:{categoryId}")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto, @PathVariable Integer userId,@PathVariable Integer categoryId)
    {
       PostDto createPost= this.postservice.createPost(postdto, userId, categoryId);
       return new ResponseEntity<>(createPost,HttpStatus.CREATED);
    }

    @GetMapping("/user:{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
        List<PostDto> posts=this.postservice.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
    @GetMapping("/category:{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer catId)
    {
        List<PostDto> posts= this.postservice.getPostByCategory(catId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }
}
