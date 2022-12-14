package com.blog.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.payload.ApiResponse;
import com.blog.payload.CommentDto;
import com.blog.services.CommentServices;
@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentServices commentService;
    @PostMapping("/postId:{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId)
    {
       CommentDto createComment= this.commentService.createComment(commentDto, postId);
     return new ResponseEntity<>(createComment,HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteComment/commentId:{commentId}")
    public ResponseEntity<ApiResponse> deleteComment (@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted Successfully!!",true),HttpStatus.OK);
    }
    
}
