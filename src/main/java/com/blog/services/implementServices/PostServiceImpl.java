package com.blog.services.implementServices;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.*;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.userRepo;
import com.blog.services.PostServices;
@Service
public class PostServiceImpl implements PostServices {
    @Autowired
    private PostRepo postrepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private userRepo userrepo;
    @Autowired
    private CategoryRepo categoryrepo;

    @Override
    public PostDto createPost(PostDto postdto,Integer userId,Integer categoryId) {
       User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "userId", userId));
       Category category=this.categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
        
       Post post= this.modelMapper.map(postdto, Post.class);
       post.setImageName("default");
       post.setAddedDate(new Date());
       post.setUser(user);
       post.setCategory(category);
       Post newpost= this.postrepo.save(post);      
        return this.modelMapper.map(newpost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postdto, Integer postId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<PostDto> getAllPost(PostDto postdto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}