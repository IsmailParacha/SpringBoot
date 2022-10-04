package com.blog.services;

import java.util.*;
import com.blog.payload.PostDto;


public interface PostServices {

    PostDto createPost(PostDto postdto,Integer userId,Integer categoryId);
    PostDto updatePost(PostDto postdto,Integer postId);
    void deletePost(Integer postId);
    List<PostDto> getAllPost(PostDto postdto);
    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
}
