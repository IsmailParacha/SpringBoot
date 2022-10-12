package com.blog.services.implementServices;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.payload.PostDto;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;
import com.blog.services.CommentServices;

public class CommentServiceImpl implements CommentServices{
    @Autowired
   private CommentRepo commentRepo;
    @Autowired
   private PostRepo postRepo;
   @Autowired
   private ModelMapper modelmapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
       Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
       Comment comment =this.modelmapper.map(commentDto, Comment.class);
       comment.setPost(post);
      Comment savedComment= this.commentRepo.save(comment);
        return this.modelmapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        
        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
        this.commentRepo.delete(comment);
    }
    
}
