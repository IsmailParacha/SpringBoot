package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/user:{userId}/category:{categoryId}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, @PathVariable Integer userId,@PathVariable Integer categoryId)
    {
       PostDto createPost= this.postservice.createPost(postdto, userId, categoryId);
       return new ResponseEntity<>(createPost,HttpStatus.CREATED);
    }
    
}
