package com.blog.services.implementServices;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.*;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
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
        public PostDto createPost(PostDto postdto, Integer userId, Integer categoryId) {
                User user = this.userrepo.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
                Category category = this.categoryrepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

                Post post = this.modelMapper.map(postdto, Post.class);
                post.setImageName("default");
                post.setAddedDate(new Date());
                post.setUser(user);
                post.setCategory(category);
                Post newpost = this.postrepo.save(post);
                return this.modelMapper.map(newpost, PostDto.class);
        }

        @Override
        public PostDto updatePost(PostDto postdto, Integer postId) {
                // TODO Auto-generated method stub
                return null;
        }

        @Override
        public void deletePost(Integer postId) {
                Post post = this.postrepo.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
                this.postrepo.delete(post);

        }

        @Override
        public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy) {

                Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
                Page<Post> pagePosts = this.postrepo.findAll(page);
                List<Post> allposts = pagePosts.getContent();
                List<PostDto> postdtos = allposts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());
                PostResponse postResponse = new PostResponse();
                postResponse.setContent(postdtos);
                postResponse.setPageNo(pagePosts.getNumber());
                postResponse.setPageSize(pagePosts.getSize());
                postResponse.setTotalPages(pagePosts.getTotalPages());
                postResponse.setTotalElements(pagePosts.getTotalElements());
                postResponse.setLastPage(pagePosts.isLast());
                return postResponse;
        }

        @Override
        public PostDto getPostById(Integer postId) {
                Post post = this.postrepo.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("POSt", "id", postId));
                return this.modelMapper.map(post, PostDto.class);
        }

        @Override
        public List<PostDto> getPostByCategory(Integer categoryId) {
                Category cat = this.categoryrepo.findById(categoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
                List<Post> posts = this.postrepo.findByCategory(cat);
                List<PostDto> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());
                return postdtos;
        }

        @Override
        public List<PostDto> getPostByUser(Integer userId) {
                User user = this.userrepo.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
                List<Post> posts = this.postrepo.findByUser(user);

                List<PostDto> postdtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                                .collect(Collectors.toList());
                return postdtos;
        }

}
