package com.blog.payload;

import java.util.Date;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private User user;
    private Category category;

    
}
